package com.starshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StarShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarShopApplication.class, args);
	}

}
