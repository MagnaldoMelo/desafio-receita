package br.com.mmelo.sincronizareceita.SpringApp.reports;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
public class CustomerResultReport extends AbstractReport<CustomerResult>{

    @Override
    public String line(CustomerResult customerResult) {
        String report = customerResult.getAgencia() + ";" + customerResult.getConta() + ";" +
                customerResult.getSaldo().toString().replace("." , ",") + ";" +
                customerResult.getStatus() + ";" + customerResult.getStatusResult() + "\n";
        return report;
    }
}
