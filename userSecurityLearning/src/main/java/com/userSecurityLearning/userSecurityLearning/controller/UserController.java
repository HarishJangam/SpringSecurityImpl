package com.userSecurityLearning.userSecurityLearning.controller;

import com.userSecurityLearning.userSecurityLearning.models.User;
import com.userSecurityLearning.userSecurityLearning.repository.UserRepository;
import com.userSecurityLearning.userSecurityLearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    UserRepository userRepository;


    PasswordEncoder bCryptPasswordEncoder;
    UserService userService;
    public  UserController(UserRepository userRepository,PasswordEncoder passwordEncoder,UserService userService){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=passwordEncoder;
        this.userService=userService;
    }

    @GetMapping("/hi")
    public String  getHiUser(){
        return "hi from hi user";
    }

    @GetMapping("/hello")
    public String getHelloUser(){
        return "hello from hello user";
    }
    @GetMapping("/login")
    public User saveUser(@RequestParam String username , @RequestParam String pass){
        User user=new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(pass));
        user.setActive(true);
        return userService.saveUser(user);
    }
}
