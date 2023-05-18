package com.example.jpamaster.config.security;

import com.example.jpamaster.users.enums.UserEnums;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JwtClaims {
    private Long userSeq;
    private String birth;
    private List<UserEnums.Role> roles;
}
