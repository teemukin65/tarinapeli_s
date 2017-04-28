package fi.teemukin65.hobby.tarinapeli;

import fi.teemukin65.hobby.tarinapeli.domain.Part;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses= Part.class)
public class TarinapeliSApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarinapeliSApplication.class, args);
	}
}
