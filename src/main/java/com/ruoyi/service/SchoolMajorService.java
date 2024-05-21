package com.ruoyi.service;

import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolMajor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;

import java.util.List;

/**
* @author ASUS
* @description 针对表【school_major】的数据库操作Service
* @createDate 2024-05-06 16:28:24
*/
public interface SchoolMajorService extends IService<SchoolMajor> {
    List<HotSchoolMajorVo> getHotSchoolMajorVoList(List<SchoolMajor> schoolMajorList);
}
