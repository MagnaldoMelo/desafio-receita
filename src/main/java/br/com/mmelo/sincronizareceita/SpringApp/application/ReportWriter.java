package br.com.mmelo.sincronizareceita.SpringApp.application;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.reports.CustomerResultReport;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

@Slf4j
public class ReportWriter {

    private String outputPath;
    private List<CustomerResult> customerResultList;

    public ReportWriter(String outputPath, List<CustomerResult> customerResultList){
        this.outputPath = outputPath;
        this.customerResultList = customerResultList;
    }

    public void write() throws IOException {
        String filename = outputPath + "/" + "report-" + Instant.now().getEpochSecond() + ".result.dat";
        String inforReports = "";

        for(CustomerResult customerResult: this.customerResultList){
            CustomerResultReport customerResultReport = new CustomerResultReport();
            inforReports += customerResultReport.line(customerResult);
        }
        Files.write(Paths.get(filename), inforReports.getBytes());
        log.info("Relatório gerado com sucesso: " + filename + "\n");
    }
}
