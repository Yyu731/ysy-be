package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    private Integer postId;

    private Long authorId;

    private String title;

    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date postTime;

    private String reviewStatus;

    private String postType;

    private static final long serialVersionUID = 1L;
}