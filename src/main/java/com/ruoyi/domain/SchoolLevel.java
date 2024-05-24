package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName school_level
 */
@TableName(value ="school_level")
@Data
public class SchoolLevel implements Serializable {
    private Integer levelId;

    private Integer schoolFeatureId;

    private Long schoolId;

    private String schoolName;

    private String featureName;

    private static final long serialVersionUID = 1L;
}