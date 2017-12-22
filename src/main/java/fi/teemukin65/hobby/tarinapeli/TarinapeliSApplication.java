package fi.teemukin65.hobby.tarinapeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EntityScan( basePackages = "fi.teemukin65.hobby.tarinapeli.domain.tables.pojos")
@PropertySource("classpath:application.properties")

public class TarinapeliSApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarinapeliSApplication.class, args);
	}
}
