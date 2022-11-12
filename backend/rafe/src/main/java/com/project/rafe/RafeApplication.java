package com.project.rafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RafeApplication.class, args);
	}

}
