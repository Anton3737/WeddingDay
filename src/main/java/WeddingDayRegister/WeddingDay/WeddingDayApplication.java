package WeddingDayRegister.WeddingDay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "WeddingDayRegister.WeddingDay.model")public class WeddingDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeddingDayApplication.class, args);
	}

}
