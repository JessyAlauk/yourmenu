package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCustomerByFirstNameUseCase {

    private final CustomerGateway customerGateway;

    public FindCustomerByFirstNameUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public List<CustomerDomain> execute(String firstName){
        return customerGateway.findByFirstName(firstName);
    }
}
