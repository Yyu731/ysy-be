package com.ruoyi.domain.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName major_subject
 */
@Data
public class MajorSubject implements Serializable {
    /**
     * 
     */
    private Integer relation_id;

    /**
     * 
     */
    private Integer subject_id;

    /**
     * 
     */
    private Integer major_id;

    /**
     * 
     */
    private String subject_name;

    /**
     * 
     */
    private String major_name;

    private static final long serialVersionUID = 1L;
}