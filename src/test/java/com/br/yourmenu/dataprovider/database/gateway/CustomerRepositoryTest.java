package com.br.yourmenu.dataprovider.database.gateway;

import com.br.yourmenu.dataprovider.database.repository.CustomerRepository;
import com.br.yourmenu.util.CustomerCreator;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for customer repository")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void init() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Save when successful")
    void save_When_Successful() {
        var customer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(customer);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getCustomerId());
        assertEquals(savedCustomer.getFirstName(), customer.getFirstName());
        assertEquals(savedCustomer.getLastName(), customer.getLastName());
        assertEquals(savedCustomer.getPhone(), customer.getPhone());
        assertEquals(savedCustomer.getDateOfBirth().toString(), String.valueOf(customer.getDateOfBirth()));
    }

    @Test
    @DisplayName("Save with First Name null")
    void save_When_Failed_First_Name_Null() {
        var customer = CustomerCreator.createCustomerWithFirstNameNull();

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.saveAndFlush(customer));
    }

    @Test
    @DisplayName("Save with Last Name null")
    void save_When_Failed_Last_Name_Null() {
        var customer = CustomerCreator.createCustomerWithLastNameNull();

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(customer));
    }

    @Test
    @DisplayName("Save with Phone null")
    void save_When_Failed_Phone_Null() {
        var customer = CustomerCreator.createCustomerWithPhoneNull();

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(customer));
    }

    @Test
    @DisplayName("Save with Date opf Birth null")
    void save_When_Failed_Date_Of_Birth_Null() {
        var customer = CustomerCreator.createCustomerWithDateOfBirthNull();

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(customer));
    }

    @Test
    @DisplayName("Find all customers")
    void find_All() {
        var customers = List.of(CustomerCreator.createCustomerToBeSaved(),
                CustomerCreator.createCustomerToBeSaved(),
                CustomerCreator.createCustomerToBeSaved(),
                CustomerCreator.createCustomerToBeSaved());

        var savedCustomers = this.customerRepository.saveAll(customers);

        var findAll = this.customerRepository.findAll();

        assertEquals(savedCustomers, findAll);
    }

    @Test
    @DisplayName("Find customer by id")
    void find_By_Id_When_Successful() {
        var customer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(customer);

        var findCustomer = this.customerRepository.findById(savedCustomer.getCustomerId());

        assertTrue(findCustomer.isPresent());
        assertEquals(savedCustomer, findCustomer.get());
    }

    @Test
    @DisplayName("Find customers by first name")
    void find_By_First_Name_When_Successful() {
        var customer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(customer);

        var findCustomer = this.customerRepository.findById(savedCustomer.getCustomerId());

        assertTrue(findCustomer.isPresent());
        assertEquals(savedCustomer, findCustomer.get());
    }

    @Test
    @DisplayName("Update customer when succeccful")
    void update_Customer_When_Successful() {
        var oldCustomer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(oldCustomer);

        var newCustomer = CustomerCreator.updateCustomer(savedCustomer.getCustomerId());
        var updatedCustomer = this.customerRepository.save(newCustomer);

        assertNotNull(updatedCustomer);
        assertNotNull(updatedCustomer.getCustomerId());
        assertEquals(updatedCustomer.getFirstName(), newCustomer.getFirstName());
        assertEquals(updatedCustomer.getLastName(), newCustomer.getLastName());
        assertEquals(updatedCustomer.getPhone(), newCustomer.getPhone());
        assertEquals(updatedCustomer.getDateOfBirth().toString(), String.valueOf(newCustomer.getDateOfBirth()));

    }

    @Test
    @DisplayName("Update with First Name null")
    void update_When_Failed_First_Name_Null() {
        var oldCustomer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(oldCustomer);

        var newCustomer = CustomerCreator.updateCustomerWithFirstNameNull(savedCustomer.getCustomerId());

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(newCustomer));
    }

    @Test
    @DisplayName("Update with Last Name null")
    void update_When_Failed_Last_Name_Null() {
        var oldCustomer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(oldCustomer);

        var newCustomer = CustomerCreator.updateCustomerWithLastNameNull(savedCustomer.getCustomerId());

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(newCustomer));
    }

    @Test
    @DisplayName("Update with Phone null")
    void update_When_Failed_Phone_Null() {
        var oldCustomer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(oldCustomer);

        var newCustomer = CustomerCreator.updateCustomerWithPhoneNull(savedCustomer.getCustomerId());

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(newCustomer));
    }

    @Test
    @DisplayName("Save with Date of Birth null")
    void update_When_Failed_Date_Of_Birth_Null() {
        var oldCustomer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(oldCustomer);

        var newCustomer = CustomerCreator.updateCustomerWithFirstNameNull(savedCustomer.getCustomerId());

        assertThrows(ConstraintViolationException.class, () -> this.customerRepository.save(newCustomer));
    }

    @Test
    @DisplayName("Delete customer")
    void delete_Customer_When_Successful() {
        var customer = CustomerCreator.createCustomerToBeSaved();
        var savedCustomer = this.customerRepository.save(customer);

        this.customerRepository.delete(savedCustomer);

        var deletedCustomer = this.customerRepository.findById(savedCustomer.getCustomerId());

        assertTrue(deletedCustomer.isEmpty());
    }

}