package com.chenze.technicalcase.web.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterWebReq implements Serializable {
    private static final long serialVersionUID = 1532846282097545996L;

    private String username;

    private String password;

    private Long roleId;

}
