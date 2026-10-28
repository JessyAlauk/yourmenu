package com.br.yourmenu.entrypoint.controller;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.usecase.InsertCustomerUseCase;
import com.br.yourmenu.entrypoint.controller.dto.mapper.CustomerMapper;
import com.br.yourmenu.entrypoint.controller.dto.requests.CustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.responses.CustomerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    private InsertCustomerUseCase insertCustomerUseCase;

    public CustomerController(InsertCustomerUseCase insertCustomerUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@RequestBody @Valid CustomerRequestDTO customerRequest){
        var customer = CustomerMapper.toSave(customerRequest);
        var customerMapper = insertCustomerUseCase.execute(customer);
        var response = CustomerMapper.toResponse(customerMapper);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
