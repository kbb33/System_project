package org.example.springboot.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.example.springboot.entity.Score;

@Mapper
public interface Scoremapper {

    @Insert("insert into score (Stuname,homework,topicid,piyu) values (#{Stuname},#{homework},#{topicid},#{piyu})")
    void insertStudent(Score score);
}
