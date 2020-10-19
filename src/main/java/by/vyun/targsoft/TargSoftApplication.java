package by.vyun.targsoft;

import by.vyun.targsoft.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TargSoftApplication implements CommandLineRunner {
    @Autowired
    private DatabaseService dbService;


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TargSoftApplication.class);
        app.run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        dbService.dbLoad();
        dbService.showTrnStatistic();
    }


}
