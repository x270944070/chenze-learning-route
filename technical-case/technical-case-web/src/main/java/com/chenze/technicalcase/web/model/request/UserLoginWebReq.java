package com.chenze.technicalcase.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginWebReq implements Serializable {
    private static final long serialVersionUID = -1722533652935125794L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
