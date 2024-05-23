package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole extends BaseEntity implements Serializable {
    private Long roleId;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String dataScope;

    private Integer menuCheckStrictly;

    private Integer deptCheckStrictly;

    private String status;

    private String delFlag;

    private static final long serialVersionUID = 1L;
}