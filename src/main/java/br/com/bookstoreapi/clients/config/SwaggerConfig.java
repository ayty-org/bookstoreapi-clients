package br.com.bookstoreapi.clients.config;

import br.com.bookstoreapi.clients.BookstoreClientsApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BookstoreClientsApplication.class.getName()))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @Primary
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName("Documentation");
        swaggerResource.setSwaggerVersion("2.0");
        swaggerResource.setLocation("/bookstore-api.yml");
        return () -> Collections.singletonList(swaggerResource);
    }
}
