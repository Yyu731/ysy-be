package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName school_info
 */
@TableName(value ="school_info")
@Data
public class SchoolInfo implements Serializable {
    private Integer schoolId;

    private String schoolName;

    private Integer schoolTypeId;

    private Integer provinceId;

    private String schoolSite;

    private String schoolPhone;

    private String schoolEmail;

    private String intro;

    private String createDate;

    private BigDecimal schoolSpace;

    private String schoolAddress;

    private Integer numDoctor;

    private Integer numMaster;

    private Integer numSubject;

    private Integer numLab;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private String schoolBadge;

    private String schoolGate;

    private int schoolHot;

    private static final long serialVersionUID = 1L;
}