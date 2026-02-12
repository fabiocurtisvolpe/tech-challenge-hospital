package com.adjt.historico.rest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Histórico de Consultas")
                        .version("1.0")
                        .description("Serviço de Histórico via GraphQL. Para testar as queries, utilize o [GraphiQL](/graphiql)\""));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("historico")
                .pathsToMatch("/graphql/**")
                .build();
    }
}