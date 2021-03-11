package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerResultLayoutTest {

    @Test
    public void testCustomeResultLayout(){
        CustomerResult customerResult;
        CustomerResultLayout customerResultLayout = new CustomerResultLayout();
        customerResult = customerResultLayout.read("3202;40011-1;-35,12;I;SUCCESS");

        assertEquals("3202", customerResult.getAgencia());
        assertEquals("40011-1", customerResult.getConta());
        assertEquals(-35.12, customerResult.getSaldo());
        assertEquals(Status.I, customerResult.getStatus());
        assertEquals(StatusResult.SUCCESS, customerResult.getStatusResult());
    }

    @Test
    public void testCustomerResultLayoutValid(){
        String lineSuccess = "0101;12225-6;100,00;A;SUCCESS";
        String lineError = "0101;12225-6;100,00";
        CustomerResultLayout customerResultLayout = new CustomerResultLayout();

        assertEquals(true, customerResultLayout.validate(lineSuccess));
        assertEquals(false, customerResultLayout.validate(lineError));
    }
}
