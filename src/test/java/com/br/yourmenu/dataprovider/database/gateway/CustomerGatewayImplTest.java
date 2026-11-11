package com.br.yourmenu.dataprovider.database.gateway;

import com.br.yourmenu.core.domain.CustomerDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CustomerGatewayImpl.class)
public class CustomerGatewayImplTest {

    private final static String NAME = "Astolfo";
    private final static String LASTNAME = "Pereira";
    private final static String PHONE = "(11) 1234-7890";

    private final static LocalDate DATE_OF_BIRTH = LocalDate.of(2005, Month.AUGUST, 8);

    @Autowired
    private CustomerGatewayImpl customerGateway;

    @Test
    void shouldSaveNewCustomWithSuccess() {
        var customer = makeCustomer(NAME, LASTNAME, PHONE, DATE_OF_BIRTH);
        var result = customerGateway.save(customer);

        assertNotNull(result);
        assertEquals(NAME, result.firstName());
        assertEquals(LASTNAME, result.lastName());
        assertEquals(PHONE, result.phone());
        assertEquals(DATE_OF_BIRTH, result.dateOfBirth());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSaveParamCustomerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> customerGateway.save(null));
    }

    @Test
    void shouldReturnCustomerWhenFindByName() {
        var customer = makeCustomer(NAME, LASTNAME, PHONE, DATE_OF_BIRTH);
        var customerSave = customerGateway.save(customer);

        var result = customerGateway.findByFirstName(NAME);
        assertThat(result, hasItems(customerSave));
    }

    @Test
    void shouldReturnEmptyWhenNotFoundCostumerByName() {
        var result = customerGateway.findByFirstName(NAME);
        assertThat(result, hasItems());
    }



    private CustomerDomain makeCustomer(String firstName,
                                        String lastName,
                                        String phone,
                                        LocalDate dateOfBirth) {
        return new CustomerDomain(
                null,
                firstName,
                lastName,
                phone,
                dateOfBirth
        );
    }
}
