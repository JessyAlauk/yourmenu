package com.br.yourmenu.core.gateway;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.entrypoint.controller.dto.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerGateway {

    CustomerDomain save(CustomerDomain customerDomain);

    List<CustomerDomain> findAll();

    Optional<CustomerDomain> findById(UUID id);

    List<CustomerDomain> findByFirstName(String firstName);

    CustomerDomain updateCustomer(CustomerDomain customerDomain);

    void deleteCustomer(UUID id);

}
