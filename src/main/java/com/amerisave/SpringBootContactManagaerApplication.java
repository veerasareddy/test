package com.amerisave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Nikhila Nervetla
 *
 */
@SpringBootApplication
@EntityScan("com.amerisave.model")
@EnableJpaRepositories("com.amerisave.repository")
public class SpringBootContactManagaerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootContactManagaerApplication.class, args);
	}
}