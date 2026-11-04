package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(UUID id){
        customerGateway.deleteCustomer(id);
    }
}
