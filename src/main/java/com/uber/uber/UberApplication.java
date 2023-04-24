package com.uber.uber;

import com.uber.uber.repository.AccountRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {com.uber.uber.repository.AccountRepo.class})
public class UberApplication {

	// 1. sign up rider and driver
	//TODO 2. complete signup rider and driver
	//TODO 3. profile for rider and driver
	//TODO 4. make a trip
	//TODO 5. handle navigation


	public static void main(String[] args) {
		SpringApplication.run(UberApplication.class, args);
	}

}
