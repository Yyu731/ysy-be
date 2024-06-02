package com.ruoyi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ReplyDto implements Serializable {
    private Integer replyId;

    private Long replierId;

    private Integer postId;

    private String replyContent;

    private Date replyTime;

    private String ipAddress;

    private static final long serialVersionUID = 1L;
}
