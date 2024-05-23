package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.SchoolInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.dto.TotalSchoolDto;

import java.util.List;

/**
* @author ASUS
* @description 针对表【school_info】的数据库操作Service
* @createDate 2024-05-06 18:43:30
*/
public interface SchoolInfoService extends IService<SchoolInfo> {

    List<BriefSchoolVo> getBriefSchoolList(List<SchoolInfo> schoolInfList);
    List<TotalSchoolVo> getTotalSchoolList(List<SchoolInfo> schoolInfList);

    Page getPage(Page<SchoolInfo> page, TotalSchoolDto totalSchoolDto);
}
