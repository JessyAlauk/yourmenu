package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.gateway.CustomerGateway;
import com.br.yourmenu.core.domain.CustomerDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InsertCustomerUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(InsertCustomerUseCase.class);

    private final CustomerGateway customerGateway;

    public InsertCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(CustomerDomain customerDomain){
        var customer = customerGateway.save(customerDomain);

        LOG.info("m=execute, customerId={}, msg='Created successfully.'", customer.customerId());
        return customer;
    }
}
