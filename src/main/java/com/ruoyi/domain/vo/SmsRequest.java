package com.ruoyi.domain.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class SmsRequest implements Serializable {
    private String phoneNumber;
}
