package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.SpringAppApplicationTest;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testes referentes a CustomerResultLayoutTest")
public class CustomerResultLayoutTest extends SpringAppApplicationTest {

    @Autowired
    private CustomerResultLayout customerResultLayout;

    @Test
    public void testCustomeResultLayout(){
        CustomerResult customerResult;
        customerResult = this.customerResultLayout.read("3202;40011-1;-35,12;I;SUCCESS");

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

        assertEquals(true, this.customerResultLayout.validate(lineSuccess));
        assertEquals(false, this.customerResultLayout.validate(lineError));
    }
}
