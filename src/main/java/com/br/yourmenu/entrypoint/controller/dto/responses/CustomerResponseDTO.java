package com.br.yourmenu.entrypoint.controller.dto.responses;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CustomerResponseDTO (String firstName,
                                   String lastName,
                                   String phone,
                                   LocalDate dateOfBirth){
}
