package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName reply_like
 */
@TableName(value ="reply_like")
@Data
public class ReplyLike implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer likeId;

    private Integer replyId;

    private Long likerId;

    private Date likeTime;

    private static final long serialVersionUID = 1L;
}