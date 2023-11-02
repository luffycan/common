package com.luffycan.commonutils.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : luffy
 * @date : 2023/11/01 17:01
 */
@Getter
@Setter
public class BaseRuntimeException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(String errorCode, String errorMsg) {
        super(errorCode + ":" + errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseRuntimeException(BaseError baseError, Throwable throwable) {
        super(baseError.getErrorCode() + ":" + baseError.getErrorMsg(), throwable);
        this.errorCode = baseError.getErrorCode();
        this.errorMsg = baseError.getErrorMsg();
    }

    public BaseRuntimeException(BaseError baseError) {
        super(baseError.getErrorCode() + ":" + baseError.getErrorMsg());
        this.errorCode = baseError.getErrorCode();
        this.errorMsg = baseError.getErrorMsg();
    }

    public BaseRuntimeException(Throwable throwable) {
        super(throwable);
    }

    public BaseRuntimeException(String message) {
        super(message);
    }
}
