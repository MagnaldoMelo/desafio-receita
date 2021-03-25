package br.com.mmelo.sincronizareceita.SpringApp.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@Slf4j
@Component
public class Observer {

    public String inputP;

    @Autowired
    private Application application;

    public Observer(@Qualifier("inputP") String inputP){
        this.inputP = inputP;
    }

    public void watch() throws Exception {
        Path path = Paths.get(inputP);
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        String message = "Aguardando novos arquivos para processamento...\n";
        log.info(message);

        while ((key = watchService.take()) != null) {
            key.pollEvents();
            application.run();
            key.reset();
            log.info(message);
        }
    }
}
