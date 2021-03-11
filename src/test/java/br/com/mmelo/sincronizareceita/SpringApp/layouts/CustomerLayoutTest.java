package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;

import static org.junit.jupiter.api.Assertions.*;

import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import org.junit.jupiter.api.Test;

public class CustomerLayoutTest {

    @Test
    public void testCustomeLayout(){
        Customer customer;
        CustomerLayout customerLayout = new CustomerLayout();
        customer = customerLayout.read("0101;12225-6;100,00;A");

        assertEquals("0101", customer.getAgencia());
        assertEquals("12225-6", customer.getConta());
        assertEquals(100.00, customer.getSaldo());
        assertEquals(Status.A, customer.getStatus());
    }

    @Test
    public void testCustomerLayoutValid(){
        String lineSuccess = "0101;12225-6;100,00;A";
        String lineError = "0101;12225-6;100,00";
        CustomerLayout customerLayout = new CustomerLayout();

        assertEquals(true, customerLayout.validate(lineSuccess));
        assertEquals(false, customerLayout.validate(lineError));
    }
}
