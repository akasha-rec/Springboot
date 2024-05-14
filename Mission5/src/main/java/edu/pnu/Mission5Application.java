package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "edu.pnu")
public class Mission5Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission5Application.class, args);
	}

}
