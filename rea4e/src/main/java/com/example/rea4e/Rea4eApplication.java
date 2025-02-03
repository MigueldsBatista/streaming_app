package com.example.rea4e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication // equivale a @Configuration, @EnableAutoConfiguration e @ComponentScan
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "com.example.rea4e")
public class Rea4eApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rea4eApplication.class, args);

        System.out.print("\033[H\033[2J");//limpa o console

        System.out.println("Aplicação rodando...");
    }

    
}

