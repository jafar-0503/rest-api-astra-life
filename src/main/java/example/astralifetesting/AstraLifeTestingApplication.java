package example.astralifetesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class AstraLifeTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstraLifeTestingApplication.class, args);
	}

}
