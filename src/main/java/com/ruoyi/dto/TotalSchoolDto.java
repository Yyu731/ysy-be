package com.ruoyi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TotalSchoolDto {

    private Integer schoolId;

    private String schoolName;

    private Integer provinveId;

    private List<Integer> provinceIds;

    private String province;

    private Integer schoolTypeId;

    private List<Integer> schoolTypeIds;

    private List<Integer> schoolFeatureIds;

    private Integer schoolHot;
}
