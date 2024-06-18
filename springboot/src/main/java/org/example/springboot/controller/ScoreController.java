package org.example.springboot.controller;

import org.example.springboot.common.Result;
import org.example.springboot.entity.Score;
import org.example.springboot.service.Scoreservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 功能：
 * 日期：2024/5/12 上午1:09
 * 问题：
 */
@CrossOrigin//解决跨域问题
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    Scoreservice scoreservice;

    @PostMapping("/insertStu")
    public Result insertStu(@RequestBody Score score){
        scoreservice.insertStudent(score);
        return Result.success(score);
    }
}