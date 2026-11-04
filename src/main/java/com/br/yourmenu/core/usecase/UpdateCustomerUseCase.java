package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public UpdateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(CustomerDomain customerDomain){
        return customerGateway.updateCustomer(customerDomain);
    }
}
