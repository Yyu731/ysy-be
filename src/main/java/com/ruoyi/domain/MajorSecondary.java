package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName major_secondary
 */
@TableName(value ="major_secondary")
@Data
public class MajorSecondary implements Serializable {
    private Integer majorSecondaryId;

    private String majorCode;

    private String majorName;

    @TableField(exist = false)
    private String parentName;

    private Integer parentId;

    private Integer degreeType;

    @TableField(exist = false)
    private Integer majorCount;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}