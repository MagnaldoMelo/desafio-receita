package br.com.mmelo.sincronizareceita.SpringApp.entities;

import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String agencia;
    private String conta;
    private Double saldo;
    private Status status;

    public CustomerResult getCustomerResult(Customer customer){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customer, CustomerResult.class);
    }
}
