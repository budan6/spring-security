package com.efruit.ark.microsvr.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 * Created by yangyang on 2018/8/17.
 */
@ControllerAdvice
@ResponseBody
public class ArkExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyException(Exception ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("flag", "FAIL");
        result.put("message", "Exception:" + ex.getMessage());
        return result;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerMyRuntimeException(RuntimeException ex) {
        Map<String,Object> result = new HashMap<>();
        result.put("flag", "FAIL");
        result.put("message", "RuntimeException:" + ex.getMessage());
        return result;
    }
}