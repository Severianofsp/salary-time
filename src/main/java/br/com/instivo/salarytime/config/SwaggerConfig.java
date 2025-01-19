package br.com.instivo.salarytime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.lang.String.format;


@Configuration
public class SwaggerConfig {

    @Value("${env-development}")
    private String envDevelopment;

    @Value("${api.host}")
    private String apiHost;

    @Bean
    public OpenAPI customizeOpenApi() {
        return new OpenAPI()
                .info(getInfo())
                .servers(getServers());
    }

    private Info getInfo() {
        return new Info()
                .title("SALARY TIME BACKEND")
                .description("Application for manager employees admission")
                .contact(getContact())
                .version("1.0.0");
    }

    private Contact getContact() {
        return new Contact()
                .name("Felipe Severiano")
                .email("severianofsp@gmail.com");
    }

    private List<Server> getServers() {
        Server localServer = new Server();

        localServer.setUrl(apiHost);
        localServer.setDescription(format("Server URL in %s environment", envDevelopment));

        return List.of(localServer);
    }
}
