package com.example.rea4e.domain.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;


@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer{
    
    @Override//NonNull serve para indicar que o método não pode retornar null
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
