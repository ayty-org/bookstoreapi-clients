package br.com.bookstoreapi.clients;

import br.com.bookstoreapi.clients.client.FilterJWT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableDiscoveryClient
public class BookstoreClientsApplication {


	@Bean
	public FilterRegistrationBean<FilterJWT> filterJwt() {
		FilterRegistrationBean<FilterJWT> filterRB = new FilterRegistrationBean<FilterJWT>();
		filterRB.setFilter(new FilterJWT());
		filterRB.addUrlPatterns("/v1/clients/*", "/auth/login");
		return filterRB;
	}
	public static void main(String[] args) {
		SpringApplication.run(BookstoreClientsApplication.class, args);
	}
}
