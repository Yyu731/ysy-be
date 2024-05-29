package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.vo.TotalMajorVo;
import com.ruoyi.service.MajorService;
import com.ruoyi.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author yyu
* @description 针对表【major】的数据库操作Service实现
* @createDate 2024-05-27 13:45:44
*/
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
    implements MajorService{

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public IPage<Major> selectTotalMajorVoList(Page<Major> page) {
        return majorMapper.selectTotalMajorVoPage(page);
    }

    @Override
    public Page<Major> selectMajorListBySecondaryId(Page<Major> page,Integer parentId) {
        LambdaQueryWrapper<Major> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Major::getParentId, parentId);
        majorMapper.selectPage(page,lambdaQueryWrapper);
        return page;
    }
}




