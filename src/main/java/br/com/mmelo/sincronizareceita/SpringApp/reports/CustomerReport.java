package br.com.mmelo.sincronizareceita.SpringApp.reports;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import org.springframework.stereotype.Component;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Component
public class CustomerReport extends AbstractReport<Customer> {

    public CustomerReport(){}

    @Override
    public String line(Customer customer) {
        String report = customer.getAgencia() + ";" + customer.getConta() + ";" +
                customer.getSaldo().toString() + ";" + customer.getStatus() + "\n";
        return report;
    }
}
