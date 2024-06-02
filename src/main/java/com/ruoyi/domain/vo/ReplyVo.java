package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ReplyVo  implements Serializable {
    private Integer replyId;

    private Long replierId;

    private Integer postId;

    private String replyContent;

    private Date replyTime;

    private String ipAddress;

    private Integer likeNum;

    private Integer likeStatus;

    private String nickName;

    private String headPortrait;

    private static final long serialVersionUID = 1L;
}
