package com.userSecurityLearning.userSecurityLearning.jwtImp.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private  String password;
}
