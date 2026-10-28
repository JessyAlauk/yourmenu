package com.br.yourmenu.dataprovider.database.gateway;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.gateway.CustomerGateway;
import com.br.yourmenu.dataprovider.database.mapper.CustomerEntityMapper;
import com.br.yourmenu.dataprovider.database.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerGatewayImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerGatewayImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDomain save(CustomerDomain customerDomain) {
        var customer = CustomerEntityMapper.toCustomerEntity(customerDomain);
        var customerEntity = customerRepository.save(customer);
        return CustomerEntityMapper.toCustomerDomain(customerEntity);
    }
}
