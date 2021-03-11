package br.com.mmelo.sincronizareceita.SpringApp.processors;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerResultLayout;
import br.com.mmelo.sincronizareceita.SpringApp.services.ReceitaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class CustomerProcessor {

    private String inputPath;
    private List<CustomerResult> customerResultList;

    public CustomerProcessor(String inputPath, List<CustomerResult> customerResultList){
        this.inputPath = inputPath;
        this.customerResultList = customerResultList;
    }

    public void process() throws IOException {
        Files.list(Paths.get(this.inputPath)).filter(p -> p.toString().endsWith(".data")).forEach(p -> {
            try {
                Stream<String> lines = Files.lines(p);
                lines.forEach(l -> {
                    try {
                        CustomerLayout customerLayout = new CustomerLayout();

                        if(customerLayout.validate(l)){
                            Customer customer = customerLayout.read(l);
                            CustomerResult customerResult = customer.getCustomerResult(customer);

                            ReceitaService receitaService = new ReceitaService();
                            Boolean transmitido = receitaService.atualizarConta(customerResult.getAgencia(),
                                    customerResult.getConta(), customerResult.getSaldo(),
                                    customerResult.getStatus().toString());

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
    }
}
