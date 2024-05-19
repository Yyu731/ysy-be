package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class HotSchoolMajorVo implements Serializable {
    private Integer schoolId;

    private String schoolName;
    private Integer majorId;
    private String majorName;

    private String schoolBadge;

    private static final long serialVersionUID = 1L;
}
