package com.ruoyi.dto;

import lombok.Data;

@Data
public class TotalMajorDto {

    private Integer majorId;

    private String majorName;

    private String degreeName;

    //专业代码
    private String majorCode;

    //一级学科只有工学
    //二级学科
    private String subDiscipline;
}
