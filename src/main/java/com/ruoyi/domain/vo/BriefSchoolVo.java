package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: BriefSchoolVo
 * Package: com.ruoyi.domain.vo
 * Description:
 *
 * @Author yy
 * @Create 2024-05-13 11:30
 * @Version 1.0
 */
@Data
public class BriefSchoolVo implements Serializable {
    private Integer schoolId;

    private String schoolName;

    private String province;

    private String schoolType;

    private String schoolGate;

    private List<String> features;

    private static final long serialVersionUID = 1L;
}
