package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.gateway.CustomerGateway;
import com.br.yourmenu.core.domain.CustomerDomain;
import org.springframework.stereotype.Service;

@Service
public class InsertCustomerUseCase {

    private final CustomerGateway customerGateway;


    public InsertCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(CustomerDomain customerDomain){
        return customerGateway.save(customerDomain);
    }
}
