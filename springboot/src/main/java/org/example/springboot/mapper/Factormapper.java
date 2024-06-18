package org.example.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springboot.entity.Question;
import org.example.springboot.entity.Questionfactor;

import java.util.List;

@Mapper
public interface Factormapper {

    @Insert("insert into `factorbank` (topicid, factor, type, point, sentence) " +
            "values (#{topicid},#{factor},#{type},#{point},#{sentence})")
    void insert(Questionfactor questionfactor);

    @Select("select factorbank.`factor` from factorbank where topicid=#{topicid}")
    List<String> selectFactor(@Param("topicid") Integer topicid);

    @Select("select factorbank.`id`, factorbank.`factor`,factorbank.`type`,factorbank.`sentence`,factorbank.`point` from factorbank where id=#{id}")
    Questionfactor selectWord(@Param("id") Integer id);

    @Select("select `id`,`factor`,`type`,`sentence`,`point` from `factorbank` where topicid=#{topicid}")
    List<Questionfactor> selectPiyu(@Param("topicid") Integer topicid);

    @Update("update factorbank set type=#{newType} where factor=#{factor} and type=#{type}")
    void updateType(@Param("factor") String factor, @Param("type") String type, @Param("newType") String newType);

    @Update("update factorbank set point=#{newPoint} where id=#{id}")
    void updatePoint(@Param("id") Integer id, @Param("newPoint") String newPoint);

    @Update("update factorbank set sentence=#{newSentence} where id=#{id}")
    void updateSentence(@Param("id") Integer id, @Param("newSentence") String newSentence);

    @Delete("delete from factorbank where factor=#{factor} and type=#{type}")
    void deleteType(@Param("factor") String factor, @Param("type") String type);

    @Delete("delete from factorbank where id=#{id}")
    void deletePiyu(@Param("id") Integer id);
}
