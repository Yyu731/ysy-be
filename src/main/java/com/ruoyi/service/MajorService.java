package com.ruoyi.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.vo.TotalMajorVo;

import java.util.List;

/**
* @author yyu
* @description 针对表【major】的数据库操作Service
* @createDate 2024-05-27 13:45:44
*/
public interface MajorService extends IService<Major> {

    IPage<Major> selectTotalMajorVoList(Page<Major> page);
}
