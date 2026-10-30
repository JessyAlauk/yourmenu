package com.br.yourmenu.entrypoint.controller.dto.responses;

import java.time.LocalDate;
import java.util.UUID;

public record GetCustomerResponseDTO(UUID customerId,
                                     String firstName,
                                     String lastName,
                                     String phone,
                                     LocalDate dateOfBirth){
}
