package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName school_type
 */
@Data
public class SchoolType implements Serializable {
    /**
     * 
     */
    private Integer school_type_id;

    /**
     * 
     */
    private String type_name;

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
    private String create_by;

    /**
     * 
     */
    private String update_by;

    /**
     * 
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}