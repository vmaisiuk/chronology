package com.andersen.chronology.trips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.andersen.chronology.trips")
public class TripsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripsApplication.class, args);
    }

}
