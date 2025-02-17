package com.gruppo3.gestioneComunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GestioneComunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneComunityApplication.class, args);
	}

}
