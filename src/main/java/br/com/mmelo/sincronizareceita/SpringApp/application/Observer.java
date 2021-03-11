package br.com.mmelo.sincronizareceita.SpringApp.application;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@Slf4j
public class Observer {

    private Application application;
    private String inputPath;

    public Observer(Application application, String inputPath){
        this.application = application;
        this.inputPath = inputPath;
    }

    public void watch() throws Exception {
        Path path = Paths.get(inputPath);
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
