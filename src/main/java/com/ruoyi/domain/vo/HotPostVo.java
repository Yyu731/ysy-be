package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class HotPostVo implements Serializable {
    private Integer postId;

    private Long authorId;

    private String title;

    private String content;

    private String headPortrait;

    private static final long serialVersionUID = 1L;
}
