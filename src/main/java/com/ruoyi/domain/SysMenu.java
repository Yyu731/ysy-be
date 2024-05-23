package com.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class SysMenu extends BaseEntity implements Serializable {
    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private String query;

    private Integer isFrame;

    private Integer isCache;

    private String menuType;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    /** 子菜单 */
    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<SysMenu>();

    private static final long serialVersionUID = 1L;
}