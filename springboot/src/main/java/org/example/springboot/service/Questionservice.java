package org.example.springboot.service;

import org.example.springboot.entity.Question;
import org.example.springboot.mapper.Questionmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.AppletViewer;

import java.util.List;

/**
 * 功能：
 * 日期：2024/4/27 下午8:45
 * 问题：注入spring容器 让controller调用
 */
@Service
public class Questionservice {

    @Autowired
    Questionmapper questionmapper;

    public List<Integer> selectChapter() {
        return questionmapper.selectChapter();
    }

    public List<Question> selectTopic(Integer chapter) {
        return questionmapper.selectTopic(chapter);
    }

    public Integer getID(String topic) {
        return questionmapper.getID(topic);
    }

    public void deleteQuestion(Integer id) {
        questionmapper.deleteQuestion(id);
    }

    public void create(Question question) {
        questionmapper.create(question);
    }

}







/*//////////////////////////////////////分割线，下面不用管///////////////////////////////////////////*/
//
//public void insertLine(String line) {
//    questionmapper.insertLine(line);
//}
//    public void insertQuestion(Question question) {
//        questionmapper.insert(question);
//    }
//
//    public void updateQuestion(Question question) {
//        questionmapper.update(question);
//    }
//

//    public void batchQuestion(List<Integer> ids) {
//        for (Integer id : ids) {
//            questionmapper.deleteQuestion(id);
//        }
//    }
//
//    public List<Question> selectALL() {
//        return questionmapper.selectAll();
//    }
//
//    public List<Question> selectBychapter(Integer chapter) {
//        return questionmapper.selectBychapter(chapter);
//    }
//
//    public String selectOne(Integer chapter) {
//        return questionmapper.selectOne(chapter);
//    }
