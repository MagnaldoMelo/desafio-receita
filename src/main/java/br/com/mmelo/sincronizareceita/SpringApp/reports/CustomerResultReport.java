package br.com.mmelo.sincronizareceita.SpringApp.reports;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import org.springframework.stereotype.Component;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Component
public class CustomerResultReport extends AbstractReport<CustomerResult>{

    public CustomerResultReport(){}

    @Override
    public String line(CustomerResult customerResult) {
        String report = customerResult.getAgencia() + ";" + customerResult.getConta() + ";" +
                customerResult.getSaldo().toString().replace("." , ",") + ";" +
                customerResult.getStatus() + ";" + customerResult.getStatusResult() + "\n";
        return report;
    }
}
