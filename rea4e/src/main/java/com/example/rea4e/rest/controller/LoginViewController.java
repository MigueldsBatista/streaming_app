package com.example.rea4e.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping
    public String loginPage() {
        return "login";
    }
    
}
