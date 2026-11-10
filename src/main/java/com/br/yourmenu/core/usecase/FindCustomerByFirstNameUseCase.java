package com.br.yourmenu.core.usecase;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindCustomerByFirstNameUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(FindCustomerByFirstNameUseCase.class);

    private final CustomerGateway customerGateway;

    public FindCustomerByFirstNameUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public List<CustomerDomain> execute(String firstName){
        var customers = customerGateway.findByFirstName(firstName);

        LOG.info("m=execute, firstName={}, msg='Deleted successfully.'", firstName);
        return customers;
    }
}
