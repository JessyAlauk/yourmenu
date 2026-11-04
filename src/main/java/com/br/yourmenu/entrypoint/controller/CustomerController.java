package com.br.yourmenu.entrypoint.controller;

import com.br.yourmenu.core.usecase.*;
import com.br.yourmenu.entrypoint.controller.dto.mapper.CustomerMapper;
import com.br.yourmenu.entrypoint.controller.dto.requests.PostCustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.requests.PutCustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.responses.GetCustomerResponseDTO;
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

    @PostMapping
    public ResponseEntity<GetCustomerResponseDTO> save(@RequestBody @Valid PostCustomerRequestDTO customerRequest){
        var customer = CustomerMapper.toSave(customerRequest);
        var customerMapper = insertCustomerUseCase.execute(customer);
        var response = CustomerMapper.toResponse(customerMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetCustomerResponseDTO> findById(@PathVariable UUID id){
        var customer = findCustomerByIdUseCase.execute(id);
        var customerResponse = CustomerMapper.toResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping
    public ResponseEntity<List<GetCustomerResponseDTO>> findAll(){
        var customers = findAllUseCase.execute();
        var customersResponse = customers
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(customersResponse);
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<GetCustomerResponseDTO>> findByFirstName(@RequestParam(name = "name") String firstName){
        var customers = findCustomerByFirstNameUseCase.execute(firstName);
        var customersResponse = customers
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(customersResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetCustomerResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid PutCustomerRequestDTO customerRequest){
        var customer = CustomerMapper.toSave(customerRequest, id);
        var customerMapper = updateCustomerUseCase.execute(customer);
        var response = CustomerMapper.toResponse(customerMapper);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid UUID id){
        deleteCustomerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
