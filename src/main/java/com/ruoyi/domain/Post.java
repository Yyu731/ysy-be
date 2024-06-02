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
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer postId;

    private Long authorId;

    @TableField(exist = false)
    private String userName;

    private String title;

    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date postTime;

    private Integer reviewStatus;

    private Integer postType;

    @TableField(exist = false)
    private Integer likeCount;

    @TableField(exist = false)
    private Integer collectCount;

    @TableField(exist = false)
    private Integer replyCount;

    @TableField(exist = false)
    private List<PostImages> postImagesList;

    private static final long serialVersionUID = 1L;
}