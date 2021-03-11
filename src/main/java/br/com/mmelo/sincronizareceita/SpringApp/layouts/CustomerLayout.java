package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.utils.FunctionString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Slf4j
public class CustomerLayout extends AbstractLayout<Customer> {

    public static final int FIELD_AGENCIA = 0;
    public static final int FIELD_CONTA = 1;
    public static final int FIELD_SALDO = 2;
    public static final int FIELD_STATUS = 3;

    @Override
    public Customer read(String line) {
        String[] fields = line.split(FIELD_DELIMITER);
        Customer customer = new Customer();
        customer.setAgencia(fields[FIELD_AGENCIA].replaceAll(" ", ""));
        customer.setConta(fields[FIELD_CONTA].replaceAll(" ", ""));
        customer.setSaldo(Double.parseDouble(fields[FIELD_SALDO].replace("," , ".").replaceAll(" ", "")));
        customer.setStatus(Status.valueOf(fields[FIELD_STATUS].replaceAll(" ", "")));

        return customer;
    }

    @Override
    public Boolean validate(String line) {
        try{
            String[] fields = line.split(FIELD_DELIMITER);

            if (fields.length == 4){
                String agencia = fields[FIELD_AGENCIA].replaceAll(" ", "");
                if (!agencia.matches("[0-9]{4}")) {
                    return false;
                }

                String conta = fields[FIELD_CONTA].replaceAll(" ", "");;
                if (!conta.matches("[0-9]{5}-[0-9]{1}")) {
                    return false;
                }

                String saldo = fields[FIELD_SALDO].replace("," , ".");
                try{
                    Double value = Double.parseDouble(saldo);
                }catch (Exception e){
                    return false;
                }

                if (Status.valueOf(fields[FIELD_STATUS]) == null){
                    return false;
                }
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
