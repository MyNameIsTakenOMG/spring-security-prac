package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepo;
import com.example.demo.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AccountRepo accountRepo;
    @GetMapping("/myAccount")
    public Account getAccountInfo(@RequestParam String email){
        List<Customer> customers = customerRepo.findByEmail(email);
        if(customers!=null && !customers.isEmpty() ){
            return accountRepo.findByCustomerId(customers.get(0).getId());
        }
        return null;
    }
}
