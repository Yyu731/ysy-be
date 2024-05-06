package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName school_province
 */
@Data
public class SchoolProvince implements Serializable {
    /**
     * 
     */
    private Integer region_id;

    /**
     * 
     */
    private String region_name;

    /**
     * 
     */
    private Date create_time;

    /**
     * 
     */
    private Date update_time;

    /**
     * 
     */
    private String update_by;

    /**
     * 
     */
    private String create_by;

    /**
     * 
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}