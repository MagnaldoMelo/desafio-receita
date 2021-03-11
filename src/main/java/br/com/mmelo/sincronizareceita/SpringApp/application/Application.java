package br.com.mmelo.sincronizareceita.SpringApp.application;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.processors.CustomerProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {

    private String inputPath;
    private String outputPath;

    public Application(String inputPath, String outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void run() throws Exception {
        List<CustomerResult> customerResultList = new ArrayList<>();
        CustomerProcessor customerProcessor = new CustomerProcessor(this.inputPath, customerResultList);
        customerProcessor.process();

        if (customerResultList.size() > 0){
            ReportWriter reportWriter = new ReportWriter(this.outputPath, customerResultList);
            reportWriter.write();
        } else {
            log.info("Não há Dados para serem processados...\n");
        }
    }
}
