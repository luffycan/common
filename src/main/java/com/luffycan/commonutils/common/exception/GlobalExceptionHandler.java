package com.luffycan.commonutils.common.exception;

import com.luffycan.commonutils.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: luffy
 * Time: 2023/11/01 17:51
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseRuntimeException.class)
    public ResponseResult handlerXmApiException(HttpServletRequest httpServletRequest, BaseRuntimeException baseRuntimeException) {
        log.error("请求路径:{}; 抛出XmApiException:", httpServletRequest.getRequestURI(), baseRuntimeException);
        return ResponseResult.fail(baseRuntimeException.getErrorCode(), baseRuntimeException.getErrorMsg());
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseResult handlerException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("请求路径:{};抛出未知异常:", httpServletRequest.getRequestURI(), e);
        return ResponseResult.fail("程序内部错误!");
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseResult handlerNullPointerException(HttpServletRequest httpServletRequest, Exception e) {
        log.error("请求路径:{};抛出空指针异常:", httpServletRequest.getRequestURI(), e);
        return ResponseResult.fail("程序内部错误!");
    }


}
