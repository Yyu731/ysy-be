package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName subject
 */
@Data
public class Subject implements Serializable {
    /**
     * 
     */
    private Integer subject_id;

    /**
     * 
     */
    private Integer subject_code;

    /**
     * 
     */
    private String subject_name;

    /**
     * 
     */
    private String subject_intro;

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

    private static final long serialVersionUID = 1L;
}