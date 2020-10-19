package by.vyun.targsoft;

import by.vyun.targsoft.service.DatabaseService;
//import by.vyun.targsoft.service.TrnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TargSoftApplication implements CommandLineRunner {

	//@Autowired
	//private TrnService trnService;
	@Autowired
	private DatabaseService dbService;


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TargSoftApplication.class);
		app.setBannerMode(Banner.Mode.LOG);
		app.run(args);

		//System.out.println("It is working!");
	}


	@Override
	public void run(String... args) throws Exception {
		dbService.dbLoad();
		//System.out.println(trnService.showData());

	}
}
