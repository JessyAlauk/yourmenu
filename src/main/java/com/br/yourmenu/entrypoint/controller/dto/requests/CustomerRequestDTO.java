package com.br.yourmenu.entrypoint.controller.dto.requests;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CustomerRequestDTO (@NotBlank String firstName,
                                  @NotBlank String lastName,
                                  @NotBlank String phone,
                                  @NotBlank LocalDate dateOfBirth){
}
