package org.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.annotations.Delete;
import org.example.springboot.common.Result;
import org.example.springboot.entity.Question;
import org.example.springboot.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//解决跨域问题
/**
 * 功能：该文件主要编写测试question数据库的增删改查功能路由，没有实际用处
 * 日期：2024/4/27 下午8:22
 *
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    Questionservice questionservice;

    /*
     * 数据的传输方式通过前端controller到service注入spring容器里 通过mapper调用方法输入数据库里面
     * */
    @GetMapping("/selectChapter")
    public Result selectChapter() {
        List<Integer> chapterList = questionservice.selectChapter();
        return Result.success(chapterList);
    }

    @GetMapping("/selectTopic/{chapter}")
    public Result selectTopic(@PathVariable Integer chapter) {
        List<Question> questionList = questionservice.selectTopic(chapter);
        return Result.success(questionList);
    }

    @GetMapping("/getID")
    public Result getID(@RequestParam String topic) {
        Integer id=questionservice.getID(topic);
        return Result.success(id);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        questionservice.deleteQuestion(id);
        return Result.success();
    }

    @PostMapping("/create")
    public Result create(@RequestBody Question question) {
        if (StrUtil.isBlank(question.getTopic()) || StrUtil.isBlankIfStr(question.getChapter())) {
            return Result.error("输入的题目或者章节为空");
        }//用这个测试前端还是后端出了问题
        questionservice.create(question);
        return Result.success(question);
    }
}




/*////////////////////////////////////////分割线，下面乱写的不用管////////////////////////////////////////////*/
//    /*
//     * 在数据库后面增加一列
//     * */
//    @PostMapping("/addline/{line}")
//    public Result addline(String line) {
//        questionservice.insertLine(line);
//        return Result.success();
//    }

//    /*
//     * 新增数据库信息
//     * */
//    @PostMapping("/add")
//    public Result add(@RequestBody Question question) {
//        questionservice.insertQuestion(question);
//        return Result.success();
//    }
//
//    /*
//     * 修改数据库信息
//     * */
//    @PutMapping("/update")
//    public Result update(@RequestBody Question question) {
//        questionservice.updateQuestion(question);
//        return Result.success();
//    }
//    /*
//     * 删除数据库信息
//     * */
//    @DeleteMapping("/delete/{id}")
//    public Result delete(@PathVariable Integer id) {
//        questionservice.deleteQuestion(id);
//        return Result.success();
//    }
//    /*
//     *批量删除
//     * */
//    @DeleteMapping("/delete/batch")
//    public Result batchdelete(@RequestBody List<Integer> ids) {
//        questionservice.batchQuestion(ids);
//        return Result.success();
//    }
//    /*
//     * 查询全部用户信息
//     * */
//    @GetMapping("/selectALL")
//    public Result selectAll() {
//        List<Question> questionList= questionservice.selectALL();
//        return Result.success(questionList);
//    }
//    /*
//     * 单条件查询用户信息
//     * */
//    @GetMapping("/selectBychapter/{chapter}")
//    public Result selectBychapter(@PathVariable Integer chapter) {
//        List<Question> questionList= questionservice.selectBychapter(chapter);
//        return Result.success(questionList);
//    }
//    /*
//     * 查询一个数据
//     * */
//    @GetMapping("/selectONE/{chapter}")
//    public Result selectONE(@PathVariable Integer chapter) {
//        String topic= questionservice.selectOne(chapter);
//        return Result.success(topic);
//    }
