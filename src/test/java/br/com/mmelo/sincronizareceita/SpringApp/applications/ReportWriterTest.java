package br.com.mmelo.sincronizareceita.SpringApp.applications;

import br.com.mmelo.sincronizareceita.SpringApp.SpringAppApplicationTest;
import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.enums.Status;
import br.com.mmelo.sincronizareceita.SpringApp.enums.StatusResult;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerResultLayout;
import br.com.mmelo.sincronizareceita.SpringApp.reports.CustomerResultReport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DisplayName("Testes referentes a ReportWriterTest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportWriterTest extends SpringAppApplicationTest {

    @Value("${processor.data.output}")
    private String outputP;

    private String filename;

    Path arquivoOutput = null;

    Boolean validado = false;

    @Autowired
    private CustomerResultReport customerResultReport;

    @Autowired
    private CustomerLayout customerLayout;

    @Autowired
    private CustomerResultLayout customerResultLayout;

    @BeforeEach
    void setUp() throws IOException {
        this.filename = this.outputP + "/report-test.data";

        this.arquivoOutput = Paths.get(this.filename);
    }

    @Order(1)
    @Test
    void write() throws IOException {
        String inforReports = "";

        List<CustomerResult> customerResultList = new ArrayList<>();

        CustomerResult customerResult = new CustomerResult();
        customerResult.setAgencia("3202");
        customerResult.setConta("40011-1");
        customerResult.setSaldo(102.25);
        customerResult.setStatus(Status.A);
        customerResult.setStatusResult(StatusResult.SUCCESS);

        customerResultList.add(customerResult);

        for(CustomerResult cResult: customerResultList){
            inforReports += this.customerResultReport.line(cResult);
        }

        Files.deleteIfExists(arquivoOutput);
        Files.write(Paths.get(filename), inforReports.getBytes());

        assertEquals(true, Files.exists(this.arquivoOutput));
    }

    @Order(2)
    @Test
    void read() throws IOException {
        this.validado = false;

        if (Files.exists(this.arquivoOutput)){
            List<Stream<String>> listaArquivos = new ArrayList<>();
            Files.list(Paths.get(this.outputP)).filter(p -> p.toString().endsWith(".data")).forEach(p -> {
                try {
                    Stream<String> lines = Files.lines(p);
                    lines.forEach(line -> {
                        CustomerResult customerResult = this.customerResultLayout.read(line);

                        String result = "3202;40011-1;102.25;A;SUCCESS";
                        String resultRetornado = customerResult.getAgencia() + ";" +
                                customerResult.getConta() + ";" +
                                customerResult.getSaldo().toString() + ";" +
                                customerResult.getStatus() + ";" +
                                customerResult.getStatusResult();

                        if (result.equals(resultRetornado)){
                            this.validado = true;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        assertEquals(true, this.validado);
    }
}
