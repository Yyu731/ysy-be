package com.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.domain.Reply;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DetailPostVo implements Serializable {
    private Integer postId;

    private Long authorId;

    private String nickName;

    private String title;

    private String content;
    @JsonFormat(pattern="MM-dd HH:mm",timezone="GMT+8")
    private Date postTime;

//    private String reviewStatus;

    private Integer postType;

    private String headPortrait;

//    private Integer reviewNum;
    private List<ReplyVo> allReply;

    private List<String> postImages;

    private Integer reviewNum;

    private Integer collectStatus;

    private Integer collectNum;

    private Integer likeNum;

    private Integer likeStatus;

    private static final long serialVersionUID = 1L;
}