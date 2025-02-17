package com.gruppo3.gestioneCurriculum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestioneCurriculumApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneCurriculumApplication.class, args);
	}

}
