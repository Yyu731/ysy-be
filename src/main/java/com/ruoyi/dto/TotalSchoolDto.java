package com.ruoyi.dto;

import lombok.Data;

import java.util.List;

@Data
public class TotalSchoolDto {

    private Integer schoolId;

    private String schoolName;

    private String province;

    private List<Integer> schoolType;


}
