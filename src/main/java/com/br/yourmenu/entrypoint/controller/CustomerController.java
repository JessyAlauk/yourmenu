package com.br.yourmenu.entrypoint.controller;

import com.br.yourmenu.core.usecase.*;
import com.br.yourmenu.entrypoint.controller.dto.mapper.CustomerMapper;
import com.br.yourmenu.entrypoint.controller.dto.requests.PostCustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.requests.PutCustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.responses.GetCustomerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final FindAllUseCase findAllUseCase;

    private final FindCustomerByFirstNameUseCase findCustomerByFirstNameUseCase;

    private final UpdateCustomerUseCase updateCustomerUseCase;

    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController(InsertCustomerUseCase insertCustomerUseCase,
                              FindCustomerByIdUseCase findCustomerByIdUseCase,
                              FindAllUseCase findAllUseCase, FindCustomerByFirstNameUseCase findCustomerByFirstNameUseCase, UpdateCustomerUseCase updateCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.findAllUseCase = findAllUseCase;
        this.findCustomerByFirstNameUseCase = findCustomerByFirstNameUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @GetMapping({"/favicon.ico", "/actuator/favicon.ico"})
    public ResponseEntity<Void> disableFavicon() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @Operation(summary = "Criar usuário novo",
            description = "Crie um usuário novo, sem repetir o numero de telefone.",
            tags = {"customer"})
    public ResponseEntity<GetCustomerResponseDTO> save(@RequestBody @Valid PostCustomerRequestDTO customerRequest) {
        var customer = CustomerMapper.toSave(customerRequest);
        var customerMapper = insertCustomerUseCase.execute(customer);
        var response = CustomerMapper.toResponse(customerMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por id",
            description = "Buscar usuario por id, deve estar no formato UUID",
            tags = {"customer"})
    public ResponseEntity<GetCustomerResponseDTO> findById(@PathVariable UUID id) {
        var customer = findCustomerByIdUseCase.execute(id);
        var customerResponse = CustomerMapper.toResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping
    @Operation(summary = "Buscar usuário por id",
            description = "Buscar usuario por id, deve estar no formato UUID",
            tags = {"customer"})
    public ResponseEntity<List<GetCustomerResponseDTO>> findAll() {
        var customers = findAllUseCase.execute();
        var customersResponse = customers
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(customersResponse);
    }

    @GetMapping("/findByName")
    @Operation(summary = "Buscar usuários por primeiro nome",
            description = "Retorna uma lista de usuarios com o primeiro nome informado",
            tags = {"customer"})
    public ResponseEntity<List<GetCustomerResponseDTO>> findByFirstName(@RequestParam(name = "name") String firstName) {
        var customers = findCustomerByFirstNameUseCase.execute(firstName);
        var customersResponse = customers
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(customersResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Altera informações do usuário",
            description = "Altera informações do usuario informado por id.",
            tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário alterado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<GetCustomerResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PutCustomerRequestDTO customerRequest) {
        var customer = CustomerMapper.toSave(customerRequest, id);
        var customerMapper = updateCustomerUseCase.execute(customer);
        var response = CustomerMapper.toResponse(customerMapper);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta usuário por id",
            description = "Deleta o usuario informado.",
            tags = {"customer"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado."),
            @ApiResponse(responseCode = "400", description = "Usuário inexistente na base de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> delete(@PathVariable @Valid UUID id) {
        deleteCustomerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
