package br.com.mmelo.sincronizareceita.SpringApp.applications;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.processors.CustomerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class Application {

    @Autowired
    private CustomerProcessor customerProcessor;

    @Autowired
    private ReportWriter reportWriter;

    public Application(){}

    public void run() throws Exception {
        List<CustomerResult> customerResultList;
        customerResultList = customerProcessor.getProcess();

        if (customerResultList.size() > 0){
            reportWriter.write(customerResultList);
        } else {
            log.info("Não há Dados para serem processados...\n");
        }
    }
}
