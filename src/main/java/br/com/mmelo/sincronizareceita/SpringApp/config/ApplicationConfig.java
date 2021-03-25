package br.com.mmelo.sincronizareceita.SpringApp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${processor.data.input}")
    private String inputPath;

    @Value("${processor.data.output}")
    private String outputPath;

    public ApplicationConfig(){}

    @Bean
    @Qualifier("inputP")
    public String getInputPath(){
        return this.inputPath;
    }

    @Bean
    @Qualifier("outputP")
    public String getOutputPath(){
        return this.outputPath;
    }
}
