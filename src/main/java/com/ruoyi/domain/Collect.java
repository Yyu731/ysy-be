package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName collect
 */
@TableName(value ="collect")
@Data
public class Collect implements Serializable {
    private Integer collectId;

    private Integer postId;

    private Long userId;

    private Date collectTime;

    private static final long serialVersionUID = 1L;
}