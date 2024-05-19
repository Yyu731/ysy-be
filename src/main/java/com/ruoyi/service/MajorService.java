package com.ruoyi.service;

import com.ruoyi.domain.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.domain.vo.TotalMajorVo;

import java.util.List;

/**
* @author ASUS
* @description 针对表【major】的数据库操作Service
* @createDate 2024-05-06 16:28:24
*/
public interface MajorService extends IService<Major> {
    List<TotalMajorVo> getMajorVoList(List<Major> majors);
}
