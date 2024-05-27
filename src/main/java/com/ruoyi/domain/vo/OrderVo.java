package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class OrderVo implements Serializable {
    private Integer relationId;

    private String orderNumber;

    private Integer userId;

    private Integer productId;

    private Date orderTime;

    private Double paymentAmount;

    private static final long serialVersionUID = 1L;
}