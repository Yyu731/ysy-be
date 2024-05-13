package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName reply
 */
@TableName(value ="reply")
@Data
public class Reply implements Serializable {
    private Integer replyId;

    private Long replierId;

    private Integer postId;

    private String replyContent;

    private Date replyTime;

    private static final long serialVersionUID = 1L;
}