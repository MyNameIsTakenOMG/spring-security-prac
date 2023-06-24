package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyCardsController {
    @GetMapping("/myCards")
    public String getBalance(){
        return "my cards route";
    }
}
