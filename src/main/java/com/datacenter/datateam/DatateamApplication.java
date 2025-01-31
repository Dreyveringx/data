package com.datacenter.datateam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.datacenter.datateam.domain.model")
@EnableJpaRepositories(basePackages = "com.datacenter.datateam.infrastructure.adapters.persistence")
public class DatateamApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatateamApplication.class, args);
	}

}
