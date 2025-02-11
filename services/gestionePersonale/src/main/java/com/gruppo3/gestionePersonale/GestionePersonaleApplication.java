package com.gruppo3.gestionePersonale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class GestionePersonaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionePersonaleApplication.class, args);
	}

}
