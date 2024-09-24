package com.gmart.gmart_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "gmart-online-api",
                email="contactgmart@gmartonline.com",
                url ="https://gmartonline.com"
        ),
        description = "Open API documentation for gmart online",
        title="Gmart-Online-API",
        version = "1.0",
        license = @License(
                name="Licencse name",
                url="https://gmartonline.com/"
        ),
        termsOfService = "Terms of Service"

) ,
        servers =
                {
                        @Server(
                                description= "Local ENV",
                                url = "http://localhost:8080"
                        ),
                        @Server(
                                description="Prudction Environment",
                                url = "https://gmartonline.com/"
                        )
                },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }

)
@SecurityScheme(
        name="bearerAuth",
        description = "JWT AUTH description",
        scheme="bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
