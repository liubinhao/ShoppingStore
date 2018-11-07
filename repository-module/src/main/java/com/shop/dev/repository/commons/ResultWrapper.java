package com.shop.dev.repository.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultWrapper {

    private boolean status;
    private int code;
    private String message;
    private Object data;

    public static ResultWrapper success(Object data){
        return new ResultWrapper(true, 200, "OK", data);
    }

    public static <T> ResultWrapper success(int totalCount, List<T> items) {
        Pageable<T> page = new Pageable<>(totalCount, items);
        return success(page);
    }

    public static ResultWrapper error(int code, String message){
        return new ResultWrapper(false, code, message, null);
    }

}
