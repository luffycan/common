package com.luffycan.commonutils.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: luffy
 * Time: 2023/10/24 11:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private Object data;
    private String message;
    private boolean success;

    public static ResponseResult success() {
        return new ResponseResult(null, "", true);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(data, "", true);
    }

    public static ResponseResult success(Object data, String message) {
        return new ResponseResult(data, message, true);
    }

    public static ResponseResult fail() {
        return new ResponseResult(null, "", false);
    }

    public static ResponseResult fail(String errorMessage) {
        return new ResponseResult(null, errorMessage, false);
    }

    public static ResponseResult fail(Object data, String errorMessage) {
        return new ResponseResult(data, errorMessage, false);
    }
}
