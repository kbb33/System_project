package org.example.springboot.entity;

import lombok.*;

/**
 * 功能：
 * 日期：2024/4/27 下午8:13
 * 问题：构造对象参数 实现get和set方法
 */
@Setter
@Getter
@Data
public class Question {
    private Integer id;
    private String topic;
    private Integer chapter;
    private String picture;
}