package com.ruoyi.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName school_level
 */
@Data
public class SchoolLevel implements Serializable {
    /**
     * 
     */
    private Integer level_id;

    /**
     * 
     */
    private Integer school_feature_id;

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
    private String feature_name;

    private static final long serialVersionUID = 1L;
}