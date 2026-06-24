package com.userSecurityLearning.userSecurityLearning.jwtImp.jwtController;

import com.userSecurityLearning.userSecurityLearning.jwtImp.Dto.UserDto;
import com.userSecurityLearning.userSecurityLearning.jwtImp.jwtService.JwtUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JwtUserController {

    AuthenticationManager authenticationManager;
    JwtUserService jwtUserService;
    public JwtUserController(AuthenticationManager authenticationManager, JwtUserService jwtUserService){
        this.authenticationManager=authenticationManager;
        this.jwtUserService=jwtUserService;
    }

    @PostMapping("/authenticate")
    public String  getAuthenticated(@RequestBody UserDto userDto){
        System.out.println(userDto.getUsername() +" "+userDto.getPassword());
       Authentication authentication= authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        if(authentication.isAuthenticated()){
            String role=authentication.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority()
                    .replace("Role_","");
            return jwtUserService.generateToken(authentication.getName(),role);
        }
        return null;
    }
}
