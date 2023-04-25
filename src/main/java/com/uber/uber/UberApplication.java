package com.uber.uber;

import com.uber.uber.repository.AccountRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class UberApplication {

	// 1. sign up rider and driver
	// 2. complete signup rider and driver
	// 3. profile for rider and driver
	//TODO 4. handle navigation
	//TODO 5. make a trip



	public static void main(String[] args) {
		SpringApplication.run(UberApplication.class, args);
	}

}
