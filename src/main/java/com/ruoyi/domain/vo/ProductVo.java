package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductVo implements Serializable {
    private Integer productId;

    private String productName;

    private Double price;

    private String description;

    private String subject;

    private String productCondition;

    private String productImage;

    private static final long serialVersionUID = 1L;
}
