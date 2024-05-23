package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.domain.Subject;
import com.ruoyi.mapper.SubjectMapper;
import com.ruoyi.service.SubjectService;
import com.ruoyi.service.SysUserService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceimpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

}

