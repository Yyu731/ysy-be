package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName school_feature
 */
@Data
public class SchoolFeature implements Serializable {
    /**
     * 
     */
    private Integer school_feature_id;

    /**
     * 
     */
    private String feature_name;

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