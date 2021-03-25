package br.com.mmelo.sincronizareceita.SpringApp.applications;

import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.reports.CustomerResultReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

@Slf4j
@Component
public class ReportWriter {

    private String outputP;

    @Autowired
    private CustomerResultReport customerResultReport;

    public ReportWriter(@Qualifier("outputP") String outputP){
        this.outputP = outputP;
    }

    public void write(List<CustomerResult> customerResultList) throws IOException {
        String filename = this.outputP + "/" + "report-" + Instant.now().getEpochSecond() + ".result.data";
        String inforReports = "";

        for(CustomerResult customerResult: customerResultList){
            inforReports += this.customerResultReport.line(customerResult);
        }
        Files.write(Paths.get(filename), inforReports.getBytes());
        log.info("Relatório gerado com sucesso: " + filename + "\n");
    }
}
