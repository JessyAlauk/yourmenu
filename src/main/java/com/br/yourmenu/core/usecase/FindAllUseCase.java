package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUseCase {

    private final CustomerGateway customerGateway;

    public FindAllUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public List<CustomerDomain> execute(){
        return customerGateway.findAll();
    }
}
