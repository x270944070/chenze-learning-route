package com.chenze.common.model.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 5030205093103994469L;


    public static final int FAIL = -1;

    /**
     * 0:成功
     */
    private int code;

    private String message;

    private T data;

    public BaseResponse() {

    }
    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess(){
        return this.code == 0;
    }

    public static <T> BaseResponse<T> success(){
        return new BaseResponse<>(0, null, null);
    }

    public static <T> BaseResponse<T> success(T t){
        return new BaseResponse<>(0,null,t);
    }

    public static <T> BaseResponse<T> successMessage(String message){
        return new BaseResponse<>(0,message,null);
    }

    public static <T> BaseResponse<T> error(String message){
        return new BaseResponse<>(FAIL,message,null);
    }

}
