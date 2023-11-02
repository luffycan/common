package com.luffycan.commonutils.common.exception;

/**
 * 自定义错误信息的枚举，与自定义异常搭配使用
 * @author : luffy
 * Time: 2023/11/01 16:56
 */
public enum ErrorEnum implements BaseError {

    /**
     * 与开关门相关的枚举值
     */
    NONEXISTENT("NONEXISTENT", "NONEXISTENT"),
    NORMAL("NORMAL", "NORMAL"),
    MAINTENANCE("MAINTENANCE", "MAINTENANCE"),
    TROUBLE("TROUBLE", "TROUBLE"),
    SHOPPING("SHOPPING", "SHOPPING"),
    NETWORKERR("NETWORKERR", "NETWORKERR"),
    ELEERR("ELEERR", "ELEERR"),
    CAMERAERR("CAMERAERR", "CAMERAERR"),
    MAINTENFORBIDDEN("MAINTENFORBIDDEN", "MAINTENFORBIDDEN"),
    NOGOODSONLINE("NOGOODSONLINE", "NOGOODSONLINE"),
    SIMINFOERROR("SIMINFOERROR", "SIMINFOERROR"),
    UNKNOWERROR("UNKNOWERROR", "未知错误!");

    private final String errorCode;
    private final String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
