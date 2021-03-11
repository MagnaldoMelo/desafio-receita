package br.com.mmelo.sincronizareceita.SpringApp;

import br.com.mmelo.sincronizareceita.SpringApp.application.Application;
import br.com.mmelo.sincronizareceita.SpringApp.application.Observer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAppApplication implements CommandLineRunner {
	@Value("${processor.data.input}")
	private String inputPath;

	@Value("${processor.data.output}")
	private String outputPath;

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Application application = new Application(inputPath, outputPath);
		application.run();

		Observer watcher = new Observer(application, inputPath);
		watcher.watch();
	}
}
