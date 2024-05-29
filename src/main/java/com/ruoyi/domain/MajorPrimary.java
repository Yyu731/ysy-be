package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @TableName major_primary
 */
@TableName(value ="major_primary")
@Data
public class MajorPrimary implements Serializable {
    private Integer majorPrimaryId;

    private String majorCode;

    private String majorName;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    @TableField(exist = false)
    private List<MajorSecondary> majorSecondaryList;

    @TableField(exist = false)
    private Integer majorSecondaryCount;

    @TableField(exist = false)
    private Integer majorCount;

    private static final long serialVersionUID = 1L;
}