package org.example.springboot.exception;

import org.example.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：自定义异常
 * 日期：2024/4/28 下午5:31
 * 问题：
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalException(Exception e) {
        return Result.error("500","系统错误");
    }
}