package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteCustomerUseCase.class);

    private final CustomerGateway customerGateway;

    public FindAllUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public List<CustomerDomain> execute(){
        var customers = customerGateway.findAll();

        LOG.info("m=execute, msg='Read successfully.'");
        return customers;
    }
}
