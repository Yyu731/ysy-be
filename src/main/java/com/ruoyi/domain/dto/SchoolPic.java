package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName school_pic
 */
@Data
public class SchoolPic implements Serializable {
    /**
     * 
     */
    private Long image_id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String image_url;

    /**
     * 
     */
    private Integer school_id;

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