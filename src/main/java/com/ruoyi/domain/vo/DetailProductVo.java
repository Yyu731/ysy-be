package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class DetailProductVo implements Serializable {
    private Integer productId;

    private String productName;

    private Double price;

//    private String description;

    private String subject;

    //一手书/二手书
    private String productCondition;

    private String productImage;

    private static final long serialVersionUID = 1L;
}
