package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName school_type
 */
@TableName(value ="school_type")
@Data
public class SchoolType implements Serializable {
    private Integer schoolTypeId;

    private String typeName;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String remark;

    private static final long serialVersionUID = 1L;
}