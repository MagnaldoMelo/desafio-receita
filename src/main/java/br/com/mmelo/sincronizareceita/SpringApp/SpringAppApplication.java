package br.com.mmelo.sincronizareceita.SpringApp;

import br.com.mmelo.sincronizareceita.SpringApp.applications.Application;
import br.com.mmelo.sincronizareceita.SpringApp.applications.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppApplication implements CommandLineRunner {

	@Autowired
	private Application application;

	@Autowired
	private Observer observer;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		application.run();

		observer.watch();
	}
}
