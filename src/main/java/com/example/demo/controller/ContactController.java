package com.example.demo.controller;

import com.example.demo.entity.Contact;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ContactController {
    @GetMapping("/contact")
    //@PreFilter("filterObject.contactName!='Test'")
    //@PostFilter("filterObject.contactName!='Test'")
    public List<Contact> getContacts(@RequestBody List<Contact> contacts){
        return new ArrayList<>();
    }
}
