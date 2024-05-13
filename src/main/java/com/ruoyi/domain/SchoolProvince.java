package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName school_province
 */
@TableName(value ="school_province")
@Data
public class SchoolProvince implements Serializable {
    private Integer regionId;

    private String regionName;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    private String createBy;

    private String remark;

    private static final long serialVersionUID = 1L;
}