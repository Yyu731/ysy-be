package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName major
 */
@Data
public class Major implements Serializable {
    /**
     * 
     */
    private Integer major_id;

    /**
     * 
     */
    private String major_code;

    /**
     * 
     */
    private String major_name;

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
    private Integer parent_major_id;

    /**
     * 
     */
    private String degree_name;

    private static final long serialVersionUID = 1L;
}