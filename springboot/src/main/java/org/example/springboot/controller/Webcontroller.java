package org.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Question;
import org.example.springboot.service.Questionservice;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 功能：提高接口返回数据
 * 日期：4月26日下午7:53
 * 问题：
 */
@RestController
public class Webcontroller {

    @Resource
    Questionservice questionservice;

    @GetMapping("/")
    public Result hello() {
        return Result.success("success");
    }
}