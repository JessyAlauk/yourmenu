package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.exception.CustomerNotFoundException;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindCustomerByIdUseCase {

    private final CustomerGateway customerGateway;

    public FindCustomerByIdUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(UUID id){
        return customerGateway.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
