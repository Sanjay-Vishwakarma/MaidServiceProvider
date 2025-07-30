package com.maid.service.provider.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    @GetMapping("/hello")
    @PreAuthorize("hasRole('USER')")
    public String userHello() {
        return "Hello from User!";
    }
}