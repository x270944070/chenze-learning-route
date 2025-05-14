package com.chenze.technicalcase.user.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = -6769185514791412865L;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Long roleId;

}
