package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName school_major
 */
@TableName(value ="school_major")
@Data
public class SchoolMajor implements Serializable {
    private Integer relationId;

    private Integer schoolId;

    private Integer majorId;

    private String schoolName;

    private String majorName;

    private static final long serialVersionUID = 1L;
}