package com.chenze.spring.security.learning.model.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String username;

    private String password;

    private String authorities;

}
