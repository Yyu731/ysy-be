package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DetailSchoolVo implements Serializable {
    private Integer schoolId;

    private String schoolName;

    private String province;

    //综合类
    private String schoolType;

    private String schoolBadge;

    //高等院校/科研院所
    private String typeFeatures;
    //985/211/双一流/自划线
    private List<String> features;

    private List<String> schoolPic;

    private String schoolSite;

    private String schoolPhone;

    private String schoolEmail;

    private String intro;

    private String createDate;

    private BigDecimal schoolSpace;

    private String schoolAddress;

    private Integer numDoctor;

    private Integer numMaster;

    private Integer numSubject;

    private Integer numLab;

    private String QSrank;

    private static final long serialVersionUID = 1L;
}
