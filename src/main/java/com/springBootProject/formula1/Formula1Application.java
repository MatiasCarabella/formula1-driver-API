package com.springBootProject.formula1;

import com.springBootProject.formula1.driver.DriverService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springBootProject.formula1.driver.Driver;

@SpringBootApplication
public class Formula1Application {

	public static void main(String[] args) {
		SpringApplication.run(Formula1Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(DriverService driverService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Driver>> typeReference = new TypeReference<List<Driver>>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/drivers.json");
			try {
				List<Driver> drivers = mapper.readValue(inputStream, typeReference);
				driverService.add(drivers);
				System.out.println("Drivers loaded!");
			} catch (IOException e) {
				System.out.println("Unable to load drivers: " + e.getMessage());
			}
		};
	}
}