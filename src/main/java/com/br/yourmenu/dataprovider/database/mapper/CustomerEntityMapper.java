package com.br.yourmenu.dataprovider.database.mapper;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.dataprovider.database.entity.CustomerEntity;

public final class CustomerEntityMapper {

    private CustomerEntityMapper() {
    }

    public static CustomerDomain toCustomerDomain(CustomerEntity customerEntity){
        return new CustomerDomain(customerEntity.getCustomerId(),
                customerEntity.getFirstName(),
                customerEntity.getLastName(),
                customerEntity.getPhone(),
                customerEntity.getDateOfBirth());
    }

    public static CustomerEntity toCustomerEntity(CustomerDomain customerDomain){
        return new CustomerEntity(customerDomain.customerId(),
                customerDomain.firstName(),
                customerDomain.lastName(),
                customerDomain.phone(),
                customerDomain.dateOfBirth());
    }
}
