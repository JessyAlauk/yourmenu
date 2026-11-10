package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.gateway.CustomerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteCustomerUseCase.class);

    private final CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(UUID id){

        LOG.info("m=execute, customerId={}, msg='Deleted successfully.'", id);
        customerGateway.deleteCustomer(id);
    }
}
