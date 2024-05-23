package com.ruoyi.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Set;

/**
* @author yyu
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2024-05-23 10:56:57
*/
public interface SysRoleService extends IService<SysRole> {

    Set<String> selectRolePermissionByUserId(Long userId);
}
