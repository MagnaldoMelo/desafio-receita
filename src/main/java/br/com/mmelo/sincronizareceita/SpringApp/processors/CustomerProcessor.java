package br.com.mmelo.sincronizareceita.SpringApp.processors;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.services.ReceitaService;
import br.com.mmelo.sincronizareceita.SpringApp.utils.FunctionString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class CustomerProcessor {

    public String inputP;

    @Autowired
    private CustomerLayout customerLayout;

    @Autowired
    private ReceitaService receitaService;

    public CustomerProcessor(@Qualifier("inputP") String inputP){
        this.inputP = inputP;
    }

    public List<CustomerResult> getProcess() throws IOException {
        List<CustomerResult> customerResultList = new ArrayList<>();
        log.error("this.inputP: " + this.inputP);
        Files.list(Paths.get(this.inputP)).filter(p -> p.toString().endsWith(".data")).forEach(p -> {
            try {
                Stream<String> lines = Files.lines(p);
                lines.forEach(l -> {
                    try {
                        if(this.customerLayout.validate(l)){
                            log.info("Processando linha: " + l);
                            Customer customer = this.customerLayout.read(l);
                            CustomerResult customerResult = customer.getCustomerResult(customer);

                            Boolean transmitido = this.receitaService.atualizarConta(FunctionString.onlyNumbers(customerResult.getAgencia()),
                                    FunctionString.onlyNumbers(customerResult.getConta()), customerResult.getSaldo(),
                                    customerResult.getStatus().toString(), true);

                            if (transmitido){
                                customerResult.setStatusResult(StatusResult.SUCCESS);
                            } else {
                                customerResult.setStatusResult(StatusResult.FAILURE);
                            }

                            customerResultList.add(customerResult);
                        } else {
                            log.error("Linha informada inconsistente! " + l);
                        }
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return customerResultList;
    }
}
