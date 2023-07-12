package com.geekbrains.shop.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Study Market Application",
                version = "1.0.0",
                description = "Проект Rest API сервиса для продажи товаров онлайн",
                termsOfService = "",
                contact = @Contact(
                        name = "Develop Team",
                        email = "team1@gmail.gmail"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        servers = {
                @Server(
                        description = "local",
                        url = "http://localhost:8888"
                )
        }


)
@SecurityScheme(
        name = "Bearer Auth",
        description = "Описание JWT авторизации",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.DEFAULT
)
public class OpenApiConfig {
}
