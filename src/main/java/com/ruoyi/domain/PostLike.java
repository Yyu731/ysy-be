package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName post_like
 */
@TableName(value ="post_like")
@Data
public class PostLike implements Serializable {
    private Integer likeId;

    private Integer postId;

    private Long likerId;

    private Date likeTime;

    private static final long serialVersionUID = 1L;
}