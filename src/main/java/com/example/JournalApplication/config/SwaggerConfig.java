package com.example.JournalApplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;  // ✅ List import kiya hai

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ravi Ki Custom API")  // ✅ Swagger title
                        .version("2.0")  // ✅ API version
                        .description("Ye customized Swagger Documentation hai"))  // ✅ Description
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("https://localhost:8081").description("Development Server"),
                        new Server().url("https://localhost:8082").description("Production Server")))
                .tags(Arrays.asList(
                        new Tag().name("AUTHENTICATION APIs"),
                        new Tag().name("USER APIs"),
                        new Tag().name("HONE APIs")


                ));
    }
}
