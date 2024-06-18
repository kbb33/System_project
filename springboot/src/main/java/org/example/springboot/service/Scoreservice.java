package org.example.springboot.service;

import org.example.springboot.entity.Score;
import org.example.springboot.mapper.Scoremapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 日期：2024/5/12 上午1:09
 * 问题：
 */
@Service
public class Scoreservice {

    @Autowired
    Scoremapper scoremapper;

    public void insertStudent(Score score){
        scoremapper.insertStudent(score);
    }
}