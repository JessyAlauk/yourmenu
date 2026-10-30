package com.br.yourmenu.entrypoint.controller.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PostCustomerRequestDTO(@NotBlank String firstName,
                                     @NotBlank String lastName,
                                     @NotBlank String phone,
                                     @NotNull LocalDate dateOfBirth){
}
