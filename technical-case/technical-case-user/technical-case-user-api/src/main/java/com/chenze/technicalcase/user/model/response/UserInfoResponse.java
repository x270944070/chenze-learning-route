package com.chenze.technicalcase.user.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResponse implements Serializable {
    private static final long serialVersionUID = -3314544663548270693L;

    private Long id;

    private String username;

    private String password;

}
