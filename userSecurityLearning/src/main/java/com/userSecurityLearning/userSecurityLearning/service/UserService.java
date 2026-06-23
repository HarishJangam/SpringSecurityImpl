package com.userSecurityLearning.userSecurityLearning.service;

import com.userSecurityLearning.userSecurityLearning.models.User;
import com.userSecurityLearning.userSecurityLearning.repository.UserRepository;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUserByUsername(String username){
         return userRepository.findByUsernameAndIsActive(username,true)
                .orElseThrow(()->new RuntimeException("userNotFound"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=getUserByUsername(username);
        System.out.println(user.getUsername() +" "+user.getPassword());
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
