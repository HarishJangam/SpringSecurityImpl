package com.userSecurityLearning.userSecurityLearning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/hi")
    public String  getHiUser(){
        return "hi from hi user";
    }

    @GetMapping("/hello")
    public String getHelloUser(){
        return "hello from hello user";
    }
}
