package io.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
		info = @Info(
				title = "TaskBuddy-Api",
				description = "...",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Jhantu Bala",
						email = "jhantubala626@gmail.com"
				),
				license = @License(
						name = "N/A"
				),
				version = "v1"
		),
		servers = {
				@Server(
						description = "DEV",
						url = "http://localhost:9001/"
				)
		}
)
@SecurityScheme(
		name = "authenticationBearer",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		description = "JWT Bearer token for authentication"
)
public class SwaggerConfig {
}
