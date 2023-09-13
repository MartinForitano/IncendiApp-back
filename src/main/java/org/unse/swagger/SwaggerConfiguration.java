package org.unse.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI openApiConfiguration() {
		return new OpenAPI().info(new Info().title("IncendiApp - Api").version("0.0.22")
				.description("IncendiApp API documentation using Open APi & Spring Doc")
				.termsOfService("http:/swagger.io/terms/")
				.license(new License().name("MIT / Apache 2.0").url("http://springdoc.org"))
				.contact(new Contact().name("Martin Tomas Foritano").email("martin.foritano.11@gmail.com")
						.url("https://www.linkedin.com/in/martin-tomas-foritano-609303153/")));
	}

}
