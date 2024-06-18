package org.example.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：接口统一返回包装类
 * 日期：2024/4/26 下午8:03
 * 问题：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";

    public static final String CODE_SYSTEM_ERROR = "500";

    /*请求的错误编码 可以看出错误类型是什么*/
    private String code;
    /*msg代表错误的详细信息*/
    private String msg;
    /*数据*/
    private Object data;

    public static Result success() {
        return Result.builder().code(CODE_SUCCESS).msg("请求成功").build();
    }
    public static Result success(Object data) {
        return new Result(CODE_SUCCESS,"请求成功",data);
    }
    public static Result error(String msg) {
        return new Result(CODE_SYSTEM_ERROR,msg,"null");
    }
    public static Result error(String code, String msg) {
        return new Result(code,msg,null);
    }
    public static Result error(String code, String msg, Object data) {
        return Result.builder().code(CODE_SYSTEM_ERROR).msg("系统错误").data(null).build();
    }
}