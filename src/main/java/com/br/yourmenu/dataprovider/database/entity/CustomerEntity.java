package com.br.yourmenu.dataprovider.database.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    private String firstName;

    private String lastName;

    @Column(name = "phone", unique = true, length = 20)
    private String phone;

    private LocalDate dateOfBirth;

    public CustomerEntity() {
    }

    public CustomerEntity(UUID customerId, String firstName, String lastName, String phone, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
