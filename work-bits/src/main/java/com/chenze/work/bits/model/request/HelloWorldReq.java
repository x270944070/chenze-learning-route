package com.chenze.work.bits.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class HelloWorldReq implements Serializable {
    private static final long serialVersionUID = -8926875006820635958L;

    private String userCode;

}
