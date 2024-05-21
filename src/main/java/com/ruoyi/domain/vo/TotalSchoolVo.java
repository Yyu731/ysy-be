package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TotalSchoolVo implements Serializable {
    private Integer schoolId;

    private String schoolName;

    private String province;

    private String schoolType;

    private String schoolBadge;

    //高等院校/科研院所
    private String typeFeatures;
    //985/211/双一流/自划线
    private List<String> features;

    private static final long serialVersionUID = 1L;
}
