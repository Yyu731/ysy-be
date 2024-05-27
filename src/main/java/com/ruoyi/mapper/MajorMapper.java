package com.ruoyi.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yyu
* @description 针对表【major】的数据库操作Mapper
* @createDate 2024-05-27 13:45:44
* @Entity com.ruoyi.domain.Major
*/
public interface MajorMapper extends BaseMapper<Major> {

    IPage selectTotalMajorVoPage(Page<Major> page);
}




