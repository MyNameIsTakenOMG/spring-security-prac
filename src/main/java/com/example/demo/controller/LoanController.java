package com.example.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    @GetMapping("/myLoan")
    //@PostAuthorize("hasRole('ROOT')")
    public String getBalance(){
        return "my loan route";
    }
}
