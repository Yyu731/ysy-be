package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName major_subject
 */
@TableName(value ="major_subject")
@Data
public class MajorSubject implements Serializable {
    private Integer relationId;

    private Integer subjectId;

    private Integer majorId;

    private String subjectName;

    private String majorName;

    private static final long serialVersionUID = 1L;
}