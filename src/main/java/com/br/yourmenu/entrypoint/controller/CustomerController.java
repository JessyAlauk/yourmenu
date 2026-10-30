package com.br.yourmenu.entrypoint.controller;

import com.br.yourmenu.core.usecase.FindAllUseCase;
import com.br.yourmenu.core.usecase.FindCustomerByFirstNameUseCase;
import com.br.yourmenu.core.usecase.FindCustomerByIdUseCase;
import com.br.yourmenu.core.usecase.InsertCustomerUseCase;
import com.br.yourmenu.entrypoint.controller.dto.mapper.CustomerMapper;
import com.br.yourmenu.entrypoint.controller.dto.requests.PostCustomerRequestDTO;
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

    public CustomerController(InsertCustomerUseCase insertCustomerUseCase,
                              FindCustomerByIdUseCase findCustomerByIdUseCase,
                              FindAllUseCase findAllUseCase, FindCustomerByFirstNameUseCase findCustomerByFirstNameUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.findAllUseCase = findAllUseCase;
        this.findCustomerByFirstNameUseCase = findCustomerByFirstNameUseCase;
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
}
