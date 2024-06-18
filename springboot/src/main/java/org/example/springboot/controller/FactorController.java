package org.example.springboot.controller;

import org.example.springboot.common.Result;
import org.example.springboot.entity.Questionfactor;
import org.example.springboot.service.Factorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 功能：
 * 日期：2024/4/30 下午8:42
 * 问题：
 */
@RestController
@RequestMapping("/factor")
public class FactorController {

    @Autowired
    Factorservice factorservice;

    @PostMapping("/add")
    public Result add(@RequestBody Questionfactor questionfactor) {
        factorservice.insertfactor(questionfactor);
        return Result.success(questionfactor);
    }

    @GetMapping("/getFactor")
    public Result getFactor(@RequestParam Integer topicid) {
        List<String> FactorList = factorservice.selectFactor(topicid);
        return Result.success(FactorList);
    }

    @GetMapping("/getWord/{id}")
    public Result getWord(@PathVariable Integer id) {
        Questionfactor factor = factorservice.selectWord(id);
        return Result.success(factor);
    }

    @GetMapping("/getPiyu")//单条件查询
    public Result getPiyu(@RequestParam Integer topicid) {
        List<Questionfactor> sentenceList = factorservice.selectPiyu(topicid);
        return Result.success(sentenceList);
    }

    @PutMapping("/updateType")
    public Result updateType(@RequestParam String factor, @RequestParam String type, @RequestParam String newType) {
        factorservice.updateType(factor,type,newType);
        return Result.success();
    }

    @PutMapping("/updatePoint/{id}")
    public Result updatePoint(@PathVariable Integer id, @RequestParam String newPoint) {
        factorservice.updatePoint(id,newPoint);
        return Result.success();
    }

    @PostMapping("/updateSen")
    public Result updateSen(@RequestParam Integer id, @RequestParam String newSentence) {
        factorservice.updateSentence(id,newSentence);
        return Result.success();
    }

    @DeleteMapping("/deleteType")
    public Result deleteType(@RequestParam String factor, @RequestParam String type) {
        factorservice.deleteType(factor,type);
        return Result.success();
    }

    @DeleteMapping("/deletePiyu/{id}")
    public Result deletePiyu(@PathVariable Integer id) {
        factorservice.deletePiyu(id);
        return Result.success();
    }
}