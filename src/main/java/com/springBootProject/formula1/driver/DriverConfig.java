package com.springBootProject.formula1.driver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DriverConfig {

    @Bean
    CommandLineRunner commandLineRunner(DriverRepository driverRepository){
        return args -> {
           Driver michael = new Driver(
                    "Michael Schumacher",
                    "Ferrari"
            );
            Driver jacques = new Driver(
                    "Jacques Villeneuve",
                    "Williams"
            );

            Driver mika = new Driver(
                    "Mika Hakkinen",
                    "McLaren"
            );
            driverRepository.saveAll(
                    List.of(michael, jacques, mika)
            );
        };
    }
}
