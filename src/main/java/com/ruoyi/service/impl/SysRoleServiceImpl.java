package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.domain.SysRole;
import com.ruoyi.domain.SysUserRole;
import com.ruoyi.mapper.SysRoleMapper;
import com.ruoyi.mapper.SysUserRoleMapper;
import com.ruoyi.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yyu
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2024-05-23 10:56:57
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {

        List<Long> roleIds = sysUserRoleMapper.selectList(
                        Wrappers.lambdaQuery(SysUserRole.class)
                                .eq(SysUserRole::getUserId, userId)
                ).stream().map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        List<SysRole> perms = new ArrayList<>();
        for(Long roleId : roleIds) {
            List<SysRole> sysRoles = sysRoleMapper.selectList(Wrappers.lambdaQuery(SysRole.class)
                    .eq(SysRole::getRoleId, roleId));
            perms.addAll(sysRoles);
        }

        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;

    }
}




