package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CustomerLayout.class, Customer.class})
public class CustomerLayoutTest {

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
