package com.ruoyi.service;

import com.ruoyi.domain.MajorPrimary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.MajorSecondary;
import com.ruoyi.mapper.MajorPrimaryMapper;

import java.util.List;

/**
* @author yyu
* @description 针对表【major_primary】的数据库操作Service
* @createDate 2024-05-27 12:37:55
*/
public interface MajorPrimaryService extends IService<MajorPrimary> {

    List<MajorPrimary> selectMajorPrimaryList();

    List<MajorSecondary> selectMajorSecondayPrimaryListByPrimaryId(Integer id);
}
