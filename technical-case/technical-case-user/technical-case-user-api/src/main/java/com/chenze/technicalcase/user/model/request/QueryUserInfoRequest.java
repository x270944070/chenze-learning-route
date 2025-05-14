package com.chenze.technicalcase.user.model.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class QueryUserInfoRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 5952248024784055298L;

    private String userName;

    private String password;

}
