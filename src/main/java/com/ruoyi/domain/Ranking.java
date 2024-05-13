package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName ranking
 */
@TableName(value ="ranking")
@Data
public class Ranking implements Serializable {
    private Integer rankId;

    private String ranking;

    private Integer schoolId;

    private String schoolName;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    private String createBy;

    private String remark;

    private static final long serialVersionUID = 1L;
}