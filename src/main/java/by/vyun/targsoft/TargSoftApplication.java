package by.vyun.targsoft;

import by.vyun.targsoft.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class TargSoftApplication implements CommandLineRunner {

	@Autowired
	private TransactionService transactionService;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TargSoftApplication.class);
		app.setBannerMode(Banner.Mode.LOG);
		app.run(args);

		//System.out.println("It is working!");
	}


	@Override
	public void run(String... args) throws Exception {

		System.out.println(transactionService.showData());

	}
}
