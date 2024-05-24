package com.ruoyi.dto;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.util.List;

@Data
public class TotalSchoolDto {

    private Integer schoolId;

    private String schoolName;

    private List<Integer> provinceId;

    private String province;

    private List<Integer> schoolTypeId;

    private List<Integer> schoolFeatureId;

    private Integer schoolHot;
}
