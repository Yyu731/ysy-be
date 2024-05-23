package com.ruoyi.service;

import com.ruoyi.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
* @author yyu
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2024-05-23 10:56:57
*/
public interface SysMenuService extends IService<SysMenu> {

    Set<String> selectMenuPermsByUserId(Long userId);

    Set<String> selectAllMenuPerms();

    List<SysMenu> selectMenuTreeByUserId(Long userId);
}
