package com.example.rea4e;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.rea4e")
public class Rea4eApplication {

    public static void main(String[] args) {
        SpringApplication.run(Rea4eApplication.class, args);

        System.out.print("\033[H\033[2J");//limpa o console

        System.out.println("Aplicação rodando...");
    }

    
}

