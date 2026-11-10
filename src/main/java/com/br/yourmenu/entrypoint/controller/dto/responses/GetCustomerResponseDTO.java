package com.br.yourmenu.entrypoint.controller.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public record GetCustomerResponseDTO(@Schema(example = "2c040451-a374-4732-8c50-38c264ea89a2") UUID customerId,
                                     @Schema(example = "Jessica") String firstName,
                                     @Schema(example = "Alauk") String lastName,
                                     @Schema(example = "11999990000") String phone,
                                     @Schema(example = "1991-06-04") LocalDate dateOfBirth){
}
