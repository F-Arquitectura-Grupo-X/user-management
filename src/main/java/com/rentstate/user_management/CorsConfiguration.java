package com.rentstate.user_management;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Maps the CORS configuration to all endpoints
                .allowedOrigins("http://localhost:4200", "http://localhost:8080","https://renstate2-0.web.app") // Specify the origins you want to allow
                .allowedMethods("*") // Allows all HTTP methods (GET, POST, PUT, etc.)
                .allowedHeaders("*") // Allows all headers
                .allowCredentials(true); // Allows credentials (cookies, authorization headers)
    }

}



