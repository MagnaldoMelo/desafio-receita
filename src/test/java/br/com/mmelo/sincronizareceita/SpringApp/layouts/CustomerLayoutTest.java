package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.SpringAppApplicationTest;
import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Testes referentes a CustomerLayoutTest")
public class CustomerLayoutTest extends SpringAppApplicationTest {

    @Autowired
    private CustomerLayout customerLayout;

    @Test
    void testCustomeLayout(){
        Customer customer;
        customer = this.customerLayout.read("0101;12225-6;100,00;A");

        assertEquals("0101", customer.getAgencia());
        assertEquals("12225-6", customer.getConta());
        assertEquals(100.00, customer.getSaldo());
        assertEquals(Status.A, customer.getStatus());
    }

    @Test
    void testCustomerLayoutValid(){
        String lineSuccess = "0101;12225-6;100,00;A";
        String lineError = "0101;12225-6;100,00";

        assertEquals(true, this.customerLayout.validate(lineSuccess));
        assertEquals(false, this.customerLayout.validate(lineError));
    }
}
