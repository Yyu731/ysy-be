package com.ruoyi.mapper;

import com.ruoyi.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author yyu
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
* @createDate 2024-05-23 10:56:57
* @Entity com.ruoyi.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuTreeByUserId(Long userId);


    List<SysMenu> selectMenuTreeAll();
}




