package com.luffycan.commonutils.common.exception;

/**
 * @author : luffy
 */
public interface BaseError {


    /**
     * 自定义的错误code
     * @return 自定义的错误code
     */
    String getErrorCode();


    /**
     * 自定义的错误信息
     * @return 自定义的错误信息
     */
    String getErrorMsg();
}
