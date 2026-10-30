package com.br.yourmenu.entrypoint.controller.dto.mapper;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.entrypoint.controller.dto.requests.PostCustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.responses.GetCustomerResponseDTO;

import java.util.UUID;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDomain toSave(PostCustomerRequestDTO postCustomerRequestDTO){
        return new CustomerDomain(null,
                postCustomerRequestDTO.firstName(),
                postCustomerRequestDTO.lastName(),
                postCustomerRequestDTO.phone(),
                postCustomerRequestDTO.dateOfBirth());
    }

    public static CustomerDomain toSave(PostCustomerRequestDTO postCustomerRequestDTO, UUID id){
        return new CustomerDomain(id,
                postCustomerRequestDTO.firstName(),
                postCustomerRequestDTO.lastName(),
                postCustomerRequestDTO.phone(),
                postCustomerRequestDTO.dateOfBirth());
    }

    public static GetCustomerResponseDTO toResponse(CustomerDomain customerDomain){
        return new GetCustomerResponseDTO(customerDomain.customerId(),
                customerDomain.firstName(),
                customerDomain.lastName(),
                customerDomain.phone(),
                customerDomain.dateOfBirth());
    }
}
