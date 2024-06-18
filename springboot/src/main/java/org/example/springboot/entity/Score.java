package org.example.springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能：学生作业名，学生作业，类型批语（一个id数组），id，标题
 * 日期：2024/5/11 下午10:41
 * 问题：学生库类
 */
@Data
@Getter
@Setter
public class Score {
     private Integer id;
     private String Stuname;
     private String homework;
     private Integer topicid;
     private String piyu;
}