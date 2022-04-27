package br.com.bookstoreapi.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookstoreClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreClientsApplication.class, args);
	}

}
