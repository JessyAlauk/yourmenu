package com.br.yourmenu.entrypoint.controller.dto.mapper;

import com.br.yourmenu.core.domain.CustomerDomain;
import com.br.yourmenu.entrypoint.controller.dto.requests.CustomerRequestDTO;
import com.br.yourmenu.entrypoint.controller.dto.responses.CustomerResponseDTO;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerDomain toSave(CustomerRequestDTO customerRequestDTO){
        return new CustomerDomain(null,
                customerRequestDTO.firstName(),
                customerRequestDTO.lastName(),
                customerRequestDTO.phone(),
                customerRequestDTO.dateOfBirth());
    }

    public static CustomerResponseDTO toResponse(CustomerDomain customerDomain){
        return new CustomerResponseDTO(customerDomain.firstName(),
                customerDomain.lastName(),
                customerDomain.phone(),
                customerDomain.dateOfBirth());
    }
}
