package br.com.mmelo.sincronizareceita.SpringApp.reports;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
public class CustomerReport extends AbstractReport<Customer> {

    @Override
    public String line(Customer customer) {
        String report = customer.getAgencia() + ";" + customer.getConta() + ";" +
                customer.getSaldo().toString() + ";" + customer.getStatus() + "\n";
        return report;
    }
}
