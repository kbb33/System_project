package org.example.springboot.service;

import org.example.springboot.entity.Question;
import org.example.springboot.entity.Questionfactor;
import org.example.springboot.mapper.Factormapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：
 * 日期：2024/4/30 下午8:38
 * 问题：
 */
@Service
public class Factorservice {

    @Autowired
    Factormapper factormapper;

    public void insertfactor(Questionfactor questionfactor) {
        factormapper.insert(questionfactor);
    }

    public List<String> selectFactor(Integer topicid) {
        return factormapper.selectFactor(topicid);
    }

    public Questionfactor selectWord(Integer id) {
        return factormapper.selectWord(id);
    }

    public List<Questionfactor> selectPiyu(Integer topicid) {
        return factormapper.selectPiyu(topicid);
    }

    public void updateType(String factor, String type,String newType) {
        factormapper.updateType(factor,type,newType);
    }

    public void updatePoint(Integer id, String newPoint) {
        factormapper.updatePoint(id,newPoint);
    }

    public void updateSentence(Integer id, String newSentence) {
        factormapper.updateSentence(id,newSentence);
    }

    public void deleteType(String factor, String type) {
        factormapper.deleteType(factor,type);
    }

    public void deletePiyu(Integer id) {
        factormapper.deletePiyu(id);
    }
}