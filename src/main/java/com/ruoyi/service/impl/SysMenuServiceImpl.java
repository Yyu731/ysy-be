package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.domain.SysMenu;
import com.ruoyi.domain.SysRole;
import com.ruoyi.domain.SysRoleMenu;
import com.ruoyi.domain.SysUserRole;
import com.ruoyi.mapper.SysMenuMapper;
import com.ruoyi.mapper.SysRoleMapper;
import com.ruoyi.mapper.SysRoleMenuMapper;
import com.ruoyi.mapper.SysUserRoleMapper;
import com.ruoyi.service.SysMenuService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yyu
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2024-05-23 10:56:57
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {

        Set<String> perms = new HashSet<String>();

        List<SysUserRole> userRoles = sysUserRoleMapper.selectList(
                Wrappers.lambdaQuery(SysUserRole.class)
                        .eq(SysUserRole::getUserId, userId)
        );

        List<SysRole> roles = userRoles.stream().map(userRole ->
                sysRoleMapper.selectOne(
                        Wrappers.lambdaQuery(SysRole.class)
                                .eq(SysRole::getRoleId, userRole.getRoleId())
                )
        ).collect(Collectors.toList());


        for (SysRole role : roles) {

            List<Long> menuIds = sysRoleMenuMapper.selectList(
                    Wrappers.lambdaQuery(SysRoleMenu.class)
                            .eq(SysRoleMenu::getRoleId, role.getRoleId())
            ).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

            for (Long menuId : menuIds) {
                perms.addAll(sysMenuMapper.selectList(
                                Wrappers.lambdaQuery(SysMenu.class)
                                        .eq(SysMenu::getMenuId, menuId)
                        ).stream().map(SysMenu::getPerms).filter(StringUtils::isNotEmpty)
                        .collect(Collectors.toSet()));
            }

        }

        return perms;
    }

    @Override
    public Set<String> selectAllMenuPerms() {
        Set<String> perms = new HashSet<String>();

        Set<String> rolePerms = sysMenuMapper.selectList(
                        Wrappers.emptyWrapper())
                .stream().map(SysMenu::getPerms)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toSet());
        perms.addAll(rolePerms);

        return perms;

    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long userId) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(userId)) {
            menus = sysMenuMapper.selectMenuTreeAll();
        } else {
            menus = sysMenuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, 0);
    }


    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, int parentId)
    {
        List<SysMenu> returnList = new ArrayList<SysMenu>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SysMenu t = (SysMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<SysMenu> list, SysMenu t)
    {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t)
    {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext())
        {
            SysMenu n = (SysMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t)
    {
        return getChildList(list, t).size() > 0;
    }

}




