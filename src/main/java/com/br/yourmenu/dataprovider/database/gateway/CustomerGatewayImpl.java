package com.br.yourmenu.dataprovider.database.gateway;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.core.exception.CustomerNotFoundException;
import com.br.yourmenu.core.gateway.CustomerGateway;
import com.br.yourmenu.dataprovider.database.mapper.CustomerEntityMapper;
import com.br.yourmenu.dataprovider.database.repository.CustomerRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public List<CustomerDomain> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerEntityMapper::toCustomerDomain)
                .toList();
    }

    @Override
    public Optional<CustomerDomain> findById(UUID id) {
        return customerRepository.findById(id)
                .map(CustomerEntityMapper::toCustomerDomain);
    }

    @Override
    public List<CustomerDomain> findByFirstName(String firstName) {
        return customerRepository.findByFirstNameContainingIgnoreCase(firstName)
                .stream()
                .map(CustomerEntityMapper::toCustomerDomain)
                .toList();
    }

    @Override
    public CustomerDomain updateCustomer(CustomerDomain customerDomain) {
        var customer = findById(customerDomain.customerId())
                .orElseThrow(() -> new CustomerNotFoundException(customerDomain.customerId()));

        var updatedCustomer = new CustomerDomain(
                customer.customerId(),
                customerDomain.firstName(),
                customerDomain.lastName(),
                customerDomain.phone(),
                customerDomain.dateOfBirth()
        );
        return save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        var customer = findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        var customerEntity = CustomerEntityMapper.toCustomerEntity(customer);
        customerRepository.delete(customerEntity);
    }
}
