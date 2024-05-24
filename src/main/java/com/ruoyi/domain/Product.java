package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    private Integer productId;

    private String productName;

    private Double price;

    private String description;

    private String subject;

    private String productCondition;

    private String productImage;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}