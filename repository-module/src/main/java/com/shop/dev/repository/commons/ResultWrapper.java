package com.shop.dev.repository.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper {

    private boolean status;
    private int code;
    private String message;
    private Object data;
    private String token;

    public ResultWrapper(boolean status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultWrapper success(Object data){
        return new ResultWrapper(true, 200, "OK", data);
    }

    public static ResultWrapper success(Object data, String token){
        return new ResultWrapper(true, 200, "OK", data, token);
    }

    public static ResultWrapper error(int code, String message){
        return new ResultWrapper(false, code, message, null);
    }

}
