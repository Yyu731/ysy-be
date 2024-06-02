package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName reply
 */
@TableName(value ="reply")
@Data
public class Reply implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer replyId;

    private Long replierId;

    @TableField(exist = false)
    private String userName;

    private Integer postId;

    private String replyContent;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;

    private String ipAddress;

    private static final long serialVersionUID = 1L;
}