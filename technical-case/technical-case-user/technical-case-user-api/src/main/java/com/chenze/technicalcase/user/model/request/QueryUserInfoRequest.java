package com.chenze.technicalcase.user.model.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class QueryUserInfoRequest implements Serializable {
    private static final long serialVersionUID = 5952248024784055298L;

    @NotBlank
    private String username;

}
