package br.com.mmelo.sincronizareceita.SpringApp;

import br.com.mmelo.sincronizareceita.SpringApp.entities.Customer;
import br.com.mmelo.sincronizareceita.SpringApp.entities.CustomerResult;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerLayout;
import br.com.mmelo.sincronizareceita.SpringApp.layouts.CustomerResultLayout;
import br.com.mmelo.sincronizareceita.SpringApp.reports.CustomerResultReport;
import br.com.mmelo.sincronizareceita.SpringApp.services.ReceitaService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {
        CustomerLayout.class, Customer.class,
        CustomerResultLayout.class, CustomerResult.class,
        ReceitaService.class, CustomerResultReport.class
})
@ActiveProfiles("test")
public class SpringAppApplicationTest {
}
