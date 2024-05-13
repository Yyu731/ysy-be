package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName major
 */
@TableName(value ="major")
@Data
public class Major implements Serializable {
    private Integer majorId;

    private String majorCode;

    private String majorName;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private Integer parentMajorId;

    private String degreeName;

    private static final long serialVersionUID = 1L;
}