package org.example.springboot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能：
 * 日期：2024/4/30 下午8:15
 * 问题：
 */
@Data
@Getter
@Setter
public class Questionfactor {
    private Integer id;
    private Integer topicid;
    private String factor;
    private String type;
    private Integer point;
    private String sentence;
}