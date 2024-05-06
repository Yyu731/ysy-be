package com.ruoyi.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName school_major
 */
@Data
public class SchoolMajor implements Serializable {
    /**
     * 
     */
    private Integer relation_id;

    /**
     * 
     */
    private Integer school_id;

    /**
     * 
     */
    private Integer major_id;

    /**
     * 
     */
    private String school_name;

    /**
     * 
     */
    private String major_name;

    private static final long serialVersionUID = 1L;
}