package com.br.yourmenu.entrypoint.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
       CustomerController.class
})
class CustomerControllerTest {

    @Test
    void save() {
    }



    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByFirstName() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}