package br.com.mmelo.sincronizareceita.SpringApp.processors;

import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.SpringAppApplicationTest;
import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.services.ReceitaService;
import br.com.mmelo.sincronizareceita.SpringApp.utils.FunctionString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DisplayName("Testes referentes a CustomerProcessorTest")
public class CustomerProcessorTest extends SpringAppApplicationTest {

    @Value("${processor.data.input}")
    private String inputP;

    @Autowired
    private CustomerLayout customerLayout;

    @Autowired
    private ReceitaService receitaService;

    @Test
    void getProcess() throws IOException{
        List<CustomerResult> customerResultList = new ArrayList<>();
        Files.list(Paths.get(this.inputP)).filter(p -> p.toString().endsWith(".data")).forEach(p -> {
            try {
                Stream<String> lines = Files.lines(p);
                lines.forEach(l -> {
                    try {
                        if(this.customerLayout.validate(l)){
                            Customer customer = this.customerLayout.read(l);
                            CustomerResult customerResult = customer.getCustomerResult(customer);

                            Boolean transmitido = this.receitaService.atualizarConta(FunctionString.onlyNumbers(customerResult.getAgencia()),
                                    FunctionString.onlyNumbers(customerResult.getConta()), customerResult.getSaldo(),
                                    customerResult.getStatus().toString(), false);

                            if (transmitido){
                                customerResult.setStatusResult(StatusResult.SUCCESS);
                            } else {
                                customerResult.setStatusResult(StatusResult.FAILURE);
                            }

                            customerResultList.add(customerResult);
                        }
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        assertEquals(4, customerResultList.size());
    }
}
