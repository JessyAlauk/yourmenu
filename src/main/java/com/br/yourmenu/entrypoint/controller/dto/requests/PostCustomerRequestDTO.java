package com.br.yourmenu.entrypoint.controller.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PostCustomerRequestDTO(@Schema(example = "Jessica") @NotBlank String firstName,
                                     @Schema(example = "Alauk") @NotBlank String lastName,
                                     @Schema(example = "11999990000") @NotBlank String phone,
                                     @Schema(example = "1991-06-04") @NotNull LocalDate dateOfBirth){
}
