package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateCustomerUseCase.class);

    private final CustomerGateway customerGateway;

    public UpdateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerDomain execute(CustomerDomain customerDomain){
        var customer =  customerGateway.updateCustomer(customerDomain);

        LOG.info("m=execute, customerId={}, msg='Updated successfully.'", customer.customerId());
        return customer;
    }
}
