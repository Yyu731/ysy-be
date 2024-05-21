package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TotalMajorVo implements Serializable {

    private Integer majorId;

    private String majorName;

    private String majorCode;

    //学位类型+一级学科+二级学科
    private String degreeName;

    private String subDiscipline;

    private String firstLevelDiscipline;

    private static final long serialVersionUID = 1L;
}
