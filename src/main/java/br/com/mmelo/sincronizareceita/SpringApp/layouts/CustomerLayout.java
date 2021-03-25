package br.com.mmelo.sincronizareceita.SpringApp;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Slf4j
@Component
public class CustomerLayout extends AbstractLayout<Customer> {

    public static final int FIELD_AGENCIA = 0;
    public static final int FIELD_CONTA = 1;
    public static final int FIELD_SALDO = 2;
    public static final int FIELD_STATUS = 3;

    @Autowired
    private Customer customer;

    public CustomerLayout(){}

    @Override
    public Customer read(String line) {
        String[] fields = line.split(FIELD_DELIMITER);
        this.customer.setAgencia(fields[FIELD_AGENCIA].replaceAll(" ", ""));
        this.customer.setConta(fields[FIELD_CONTA].replaceAll(" ", ""));
        this.customer.setSaldo(Double.parseDouble(fields[FIELD_SALDO].replace("," , ".").replaceAll(" ", "")));
        this.customer.setStatus(Status.valueOf(fields[FIELD_STATUS].replaceAll(" ", "")));

        return this.customer;
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
