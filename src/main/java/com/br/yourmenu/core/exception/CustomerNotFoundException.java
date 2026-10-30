package com.br.yourmenu.core.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Customer not found by id: %d";

    public CustomerNotFoundException(UUID id) {
        super(String.format(DEFAULT_MESSAGE, id));
    }
}
