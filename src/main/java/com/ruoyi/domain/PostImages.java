package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName post_images
 */
@TableName(value ="post_images")
@Data
public class PostImages implements Serializable {
    private Integer imageId;

    private String imageUrl;

    private Integer postId;

    private static final long serialVersionUID = 1L;
}