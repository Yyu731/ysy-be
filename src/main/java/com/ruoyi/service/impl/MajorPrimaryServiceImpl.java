package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.MajorPrimary;
import com.ruoyi.domain.MajorSecondary;
import com.ruoyi.mapper.MajorMapper;
import com.ruoyi.mapper.MajorSecondaryMapper;
import com.ruoyi.service.MajorPrimaryService;
import com.ruoyi.mapper.MajorPrimaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yyu
* @description 针对表【major_primary】的数据库操作Service实现
* @createDate 2024-05-27 12:37:55
*/
@Service
public class MajorPrimaryServiceImpl extends ServiceImpl<MajorPrimaryMapper, MajorPrimary>
    implements MajorPrimaryService{

    @Autowired
    private MajorPrimaryMapper majorPrimaryMapper;

    @Autowired
    private MajorSecondaryMapper majorSecondaryMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<MajorPrimary> selectMajorPrimaryList() {
        List<MajorPrimary> majorPrimaryList = majorPrimaryMapper.selectList(Wrappers.emptyWrapper());
        majorPrimaryList.stream().forEach(majorPrimary -> {
            List<MajorSecondary> majorSecondaryList = majorSecondaryMapper.selectListByParentId(majorPrimary.getMajorPrimaryId());
//            for (MajorSecondary majorSecondary : majorSecondaryList) {
//                majorSecondary.setMajorCount(majorMapper.selectCountBySecondaryId(majorSecondary.getMajorSecondaryId()));
//            }
//            majorPrimary.setMajorSecondaryList(majorSecondaryList);
            majorPrimary.setMajorSecondaryCount(majorSecondaryList.size());
            majorPrimary.setMajorCount(majorMapper.selectCountByPrimaryId(majorPrimary.getMajorPrimaryId()));
        });
        return majorPrimaryList;
    }

    @Override
    public List<MajorSecondary> selectMajorSecondayPrimaryListByPrimaryId(Integer id) {
        List<MajorSecondary> majorSecondaryList = majorSecondaryMapper.selectListByParentId(id);
        for (MajorSecondary majorSecondary : majorSecondaryList) {
            majorSecondary.setMajorCount(majorMapper.selectCountBySecondaryId(majorSecondary.getMajorSecondaryId()));
        }
        return majorSecondaryList;
    }
}




