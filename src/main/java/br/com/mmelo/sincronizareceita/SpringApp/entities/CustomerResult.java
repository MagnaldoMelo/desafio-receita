package br.com.mmelo.sincronizareceita.SpringApp.entities;

import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

/**
 * @author magnaldo_melo<magnaldo.melo@gmail.com>
 */
@Data
@NoArgsConstructor
public class CustomerResult extends Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private StatusResult statusResult;

    public Customer getCustomer(CustomerResult customerResult){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(customerResult, Customer.class);
    }
}
