package com.shop.dev.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultWrapper {

    private boolean status;
    private int code;
    private String message;
    private Object data;

    public ResultWrapper(Object data) {
        this.data = data;
    }

    public ResultWrapper(boolean status, int code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultWrapper success(Object data) {
        return new ResultWrapper(true, 200, "OK", data);
    }

    public static <T> ResultWrapper success(int page, int size, int totalCount, List<T> items) {
        Pageable<T> pageable = new Pageable<>(page, size, totalCount, items);
        return success(pageable);
    }

    public static ResultWrapper error(int code, String message) {
        return new ResultWrapper(false, code, message, null);
    }

}
