package com.ruoyi.domain.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ranking
 */
@Data
public class Ranking implements Serializable {
    /**
     * 
     */
    private Integer rank_id;

    /**
     * 
     */
    private String ranking;

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