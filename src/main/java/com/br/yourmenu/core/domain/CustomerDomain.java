package com.br.yourmenu.core.domain;

import java.time.LocalDate;
import java.util.UUID;

public record CustomerDomain(UUID customerId,
                             String firstName,
                             String lastName,
                             String phone,
                             LocalDate dateOfBirth) {

}
