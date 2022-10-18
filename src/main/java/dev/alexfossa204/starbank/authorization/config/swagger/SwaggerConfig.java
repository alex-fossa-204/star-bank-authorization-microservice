package dev.alexfossa204.starbank.authorization.config.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("a-bank-public")
                .pathsToMatch("/star-bank/**")
                .packagesToScan("by.andersen.abank.microservice.controller")
                .build();
    }

    @Bean
    public OpenAPI affinityBankOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Star-Bank Authorization Microservice")
                        .description("This Microservice is responsible for user identification, authentication, authorization and credential recovery processes in the Star Bank Microservice Ecosystem. More Detailed information about component functionality - read below.")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("A-Bank Wiki Documentation")
                        .url("https://wiki.andersenlab.com/display/VMN/Onboarding"));
    }
}