package org.example.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springboot.entity.Question;

import java.util.List;

@Mapper
public interface Questionmapper {

    @Select("select DISTINCT questionbank.`chapter` from `questionbank`")
    List<Integer> selectChapter();

    @Select("select questionbank.`id`, questionbank.`topic` ,questionbank.`chapter` from `questionbank` where chapter=#{chapter}")
    List<Question> selectTopic(@Param("chapter") Integer chapter);

    @Delete("delete  from  questionbank where `id`=#{id}")
    void deleteQuestion(@Param("id") Integer id);

    @Insert("insert  into  questionbank (topic, chapter,picture) " +
            "values (#{topic}, #{chapter},#{picture})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Question question);

    @Select("select `id` from questionbank where topic=#{topic}")
    Integer getID(@Param("topic") String topic);
}


//    /*//////////////////////////////////////////////分割线下面不用管//////////////////////////////////////////////////*/
//    @Insert("ALTER TABLE `questionbank` ADD line VARCHAR(255)")
//    void insertLine(String line);
//    @Insert("insert  into  `questionbank` (topic, chapter, picture) " +
//            "values (#{topic}, #{chapter}, #{picture})")
//    void insert(Question question);
//
//    @Update("update `questionbank` set topic=#{topic}, chapter=#{chapter}, picture=#{picture} " +
//            "where id=#{id}")
//    void update(Question question);
//
//    @Select("select * from `questionbank`")
//    List<Question> selectAll();
//
//    @Select("select * from `questionbank` where chapter=#{chapter} order by id desc")
//    List<Question> selectBychapter(Integer chapter);
//
//    @Select("select questionbank.`topic` from questionbank where chapter=#{chapter} order by id desc")
//    String selectOne(Integer chapter);


