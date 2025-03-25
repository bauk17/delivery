package com.bauk.deliveryrequest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info().title("Delivery Order API").version("1.0.0").description("API documentation using OpenAPI")
                        .contact(new Contact().name("Cauã Pereira").email("cauapereira_@hotmail.com")
                                .url("https://github.com/bauk17")));
    }
}
