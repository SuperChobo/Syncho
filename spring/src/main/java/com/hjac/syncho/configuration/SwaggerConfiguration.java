package com.hjac.syncho.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost/swagger-ui/index.html

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("Syncho API 명세서").description(
				"<h3>SPACETRIP API Reference for Developers</h3>")
				.version("v1").contact(new io.swagger.v3.oas.models.info.Contact().name("huiju")
						.email("gmlwn6316@gmail.com").url("http://edu.ssafy.com"));

		return new OpenAPI().components(new Components()).info(info);
	}
	
	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder().group("All").pathsToMatch("/**").build();
	}
}