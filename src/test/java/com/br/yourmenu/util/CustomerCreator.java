package com.br.yourmenu.util;

import com.br.yourmenu.dataprovider.database.entity.CustomerEntity;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class CustomerCreator {

    private static final Faker FAKER =  new Faker(Locale.of("pt-BR"));

    public static CustomerEntity createCustomerToBeSaved(){
        return new CustomerEntity(null, firstName(), lastName(), phone(), dateOfBirth());
    }

    public static CustomerEntity createCustomerWithFirstNameNull(){
        return new CustomerEntity(null, null, lastName(), phone(), dateOfBirth());
    }

    public static CustomerEntity createCustomerWithLastNameNull(){
        return new CustomerEntity(null, firstName(), null, phone(), dateOfBirth());
    }

    public static CustomerEntity createCustomerWithPhoneNull(){
        return new CustomerEntity(null, firstName(), lastName(), null, dateOfBirth());
    }

    public static CustomerEntity createCustomerWithDateOfBirthNull(){
        return new CustomerEntity(null, firstName(), lastName(), phone(), null);
    }

    public static CustomerEntity updateCustomer(UUID id){
        return new CustomerEntity(id,
                firstName(), lastName(), phone(), dateOfBirth());
    }

    public static CustomerEntity updateCustomerWithFirstNameNull(UUID id){
        return new CustomerEntity(id, null, lastName(), phone(), dateOfBirth());
    }

    public static CustomerEntity updateCustomerWithLastNameNull(UUID id){
        return new CustomerEntity(id, firstName(), null, phone(), dateOfBirth());
    }

    public static CustomerEntity updateCustomerWithPhoneNull(UUID id){
        return new CustomerEntity(id, firstName(), lastName(), null, dateOfBirth());
    }

    public static CustomerEntity updateCustomerWithDateOfBirthNull(UUID id){
        return new CustomerEntity(id, firstName(), lastName(), phone(), null);
    }

    private static String firstName(){
        return FAKER.name().firstName();
    }

    private static String lastName(){
        return FAKER.name().lastName();
    }

    private static String phone(){
        return FAKER.phoneNumber().cellPhone();
    }

    private static LocalDate dateOfBirth(){
        return FAKER.timeAndDate().birthday(18, 65);
    }
}
