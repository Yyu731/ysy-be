package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName school_info
 */
@Data
public class SchoolInfo implements Serializable {
    /**
     * 
     */
    private Integer school_id;

    /**
     * 
     */
    private String school_name;

    /**
     * 
     */
    private Integer school_type_id;

    /**
     * 
     */
    private Integer province_id;

    /**
     * 
     */
    private String school_site;

    /**
     * 
     */
    private String school_phone;

    /**
     * 
     */
    private String school_email;

    /**
     * 
     */
    private String intro;

    /**
     * 
     */
    private String create_date;

    /**
     * 
     */
    private BigDecimal school_space;

    /**
     * 
     */
    private String school_address;

    /**
     * 
     */
    private Integer num_doctor;

    /**
     * 
     */
    private Integer num_master;

    /**
     * 
     */
    private Integer num_subject;

    /**
     * 
     */
    private Integer num_lab;

    /**
     * 
     */
    private String create_by;

    /**
     * 
     */
    private Date create_time;

    /**
     * 
     */
    private String update_by;

    /**
     * 
     */
    private Date update_time;

    /**
     * 
     */
    private String remark;

    /**
     * 
     */
    private String school_badge;

    private static final long serialVersionUID = 1L;
}