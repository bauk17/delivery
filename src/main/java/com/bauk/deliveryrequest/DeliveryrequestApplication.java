package com.bauk.deliveryrequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DeliveryrequestApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();

		dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));

		SpringApplication.run(DeliveryrequestApplication.class, args);
	}

}
