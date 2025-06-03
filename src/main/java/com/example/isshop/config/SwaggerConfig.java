package com.example.isshop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("My Spring Boot API Document")
                .version("1.0.0");

        var basicSchemeName = "HTTP Basic Auth";
        var jwtSchemeName = "JWT Auth";
        var securityRequirement = new SecurityRequirement()
                .addList(basicSchemeName)
                .addList(jwtSchemeName);
        var components = new Components()
                //使用HTTP Basic 認證
//                .addSecuritySchemes(basicSchemeName,
//                        new SecurityScheme()
//                                .name(basicSchemeName)
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("basic"))
                //使用JWT認證
                .addSecuritySchemes(jwtSchemeName,
                        new SecurityScheme()
                                .name(jwtSchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
