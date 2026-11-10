package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.exception.CustomerNotFoundException;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindCustomerByIdUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(FindCustomerByFirstNameUseCase.class);

    private final CustomerGateway customerGateway;

    public FindCustomerByIdUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(UUID id){
        var customer = customerGateway.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        LOG.info("m=execute, customerId={}, msg='Read successfully.'", id);
        return customer;
    }
}
