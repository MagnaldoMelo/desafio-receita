package br.com.mmelo.sincronizareceita.SpringApp.layouts;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.AbstractLayout;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
public class CustomerResultLayout extends AbstractLayout<CustomerResult> {

    public static final int FIELD_AGENCIA = 0;
    public static final int FIELD_CONTA = 1;
    public static final int FIELD_SALDO = 2;
    public static final int FIELD_STATUS = 3;
    public static final int FIELD_STATUS_RESULT = 4;

    @Autowired
    private CustomerResult customerResult;

    public CustomerResultLayout(){}

    @Override
    public CustomerResult read(String line) {
        String[] fields = line.split(FIELD_DELIMITER);
        this.customerResult.setAgencia(fields[FIELD_AGENCIA].replaceAll(" ", ""));
        this.customerResult.setConta(fields[FIELD_CONTA].replaceAll(" ", ""));
        this.customerResult.setSaldo(Double.parseDouble(fields[FIELD_SALDO].replace("," , ".").replaceAll(" ", "")));
        this.customerResult.setStatus(Status.valueOf(fields[FIELD_STATUS].replaceAll(" ", "")));
        this.customerResult.setStatusResult(StatusResult.valueOf(fields[FIELD_STATUS_RESULT].replaceAll(" ", "")));

        return this.customerResult;
    }

    @Override
    public Boolean validate(String line) {
        try{
            String[] fields = line.split(FIELD_DELIMITER);

            if (fields.length == 5){
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

                if (StatusResult.valueOf(fields[FIELD_STATUS_RESULT]) == null){
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
