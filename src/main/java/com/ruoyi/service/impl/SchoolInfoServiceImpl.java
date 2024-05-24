package com.ruoyi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolLevel;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.mapper.SchoolFeatureMapper;
import com.ruoyi.mapper.SchoolInfoMapper;
import com.ruoyi.mapper.SchoolLevelMapper;
import com.ruoyi.service.SchoolInfoService;
import com.ruoyi.service.SchoolLevelService;
import com.ruoyi.service.SchoolProvinceService;
import com.ruoyi.service.SchoolTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ASUS
 * @description 针对表【school_info】的数据库操作Service实现
 * @createDate 2024-05-06 18:43:30
 */
@Service
public class SchoolInfoServiceImpl extends ServiceImpl<SchoolInfoMapper, SchoolInfo>
        implements SchoolInfoService {

    @Autowired
    private SchoolProvinceService schoolProvinceService;
    @Autowired
    private SchoolTypeService schoolTypeService;

    @Autowired
    private SchoolLevelService schoolLevelService;

    @Autowired
    private SchoolInfoMapper schoolInfoMapper;

    @Autowired
    private SchoolLevelMapper schoolLevelMapper;
    @Autowired
    private SchoolFeatureMapper schoolFeatureMapper;

    @Override
    public List<BriefSchoolVo> getBriefSchoolList(List<SchoolInfo> schoolInfList) {

        List<BriefSchoolVo> briefSchoolVos = new ArrayList<>();
        int num = 0;
        for (SchoolInfo schoolInfo : schoolInfList) {
            num++;
            if (schoolInfo.getSchoolHot() == 1) {
                BriefSchoolVo briefSchoolVo = new BriefSchoolVo();
                briefSchoolVo.setSchoolId(schoolInfo.getSchoolId());
                briefSchoolVo.setSchoolName(schoolInfo.getSchoolName());
                briefSchoolVo.setSchoolGate(schoolInfo.getSchoolGate());
                briefSchoolVo.setProvince(schoolProvinceService.getById(schoolInfo.getProvinceId()).getRegionName());
                briefSchoolVo.setSchoolType(schoolTypeService.getById(schoolInfo.getSchoolTypeId()).getTypeName());
                briefSchoolVo.setFeatures(
                        schoolLevelService.list(
                                        Wrappers.lambdaQuery(SchoolLevel.class)
                                                .in(SchoolLevel::getSchoolFeatureId, 1, 2, 3, 4)
                                                .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
                                .stream().map(item -> item.getFeatureName()).limit(3)
                                .collect(Collectors.toList()));
                briefSchoolVos.add(briefSchoolVo);
            }
            System.out.println(num);
        }
        for (BriefSchoolVo briefSchoolVo : briefSchoolVos) {
            System.out.println(briefSchoolVo.getSchoolName());
        }
        return briefSchoolVos;
    }

    @Override
    public List<TotalSchoolVo> getTotalSchoolList(List<SchoolInfo> schoolInfList) {
        List<TotalSchoolVo> totalSchoolVos = new ArrayList<>();
        for (SchoolInfo schoolInfo : schoolInfList) {
            TotalSchoolVo totalSchoolVo = new TotalSchoolVo();
            totalSchoolVo.setSchoolId(schoolInfo.getSchoolId());
            totalSchoolVo.setSchoolName(schoolInfo.getSchoolName());
            totalSchoolVo.setSchoolBadge(schoolInfo.getSchoolBadge());
            totalSchoolVo.setProvince(schoolProvinceService.getById(schoolInfo.getProvinceId()).getRegionName());
            totalSchoolVo.setSchoolType(schoolTypeService.getById(schoolInfo.getSchoolTypeId()).getTypeName());
            List<SchoolLevel> schoolLevel = schoolLevelService.list(Wrappers.<SchoolLevel>lambdaQuery()
                    .in(SchoolLevel::getSchoolFeatureId, 5, 6)
                    .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId())
            );
            for (SchoolLevel schoolLevel1 : schoolLevel) {
                totalSchoolVo.setTypeFeatures(schoolLevel1.getFeatureName());
            }
            totalSchoolVo.setFeatures(
                    schoolLevelService.list(
                                    Wrappers.lambdaQuery(SchoolLevel.class)
                                            .in(SchoolLevel::getSchoolFeatureId, 1, 2, 3, 4)
                                            .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
                            .stream().map(item -> item.getFeatureName()).limit(3)
                            .collect(Collectors.toList()));
            totalSchoolVos.add(totalSchoolVo);
        }

        for (TotalSchoolVo totalSchoolVo : totalSchoolVos) {
            System.out.println(totalSchoolVo.getSchoolName());
        }
        return totalSchoolVos;
    }

    @Override
    public Page getPage(Page<SchoolInfo> page, TotalSchoolDto totalSchoolDto) {

        LambdaQueryWrapper<SchoolInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(totalSchoolDto.getSchoolName())) {
            lambdaQueryWrapper.like(SchoolInfo::getSchoolName, totalSchoolDto.getSchoolName());
        }

        schoolInfoMapper.selectPage(page, lambdaQueryWrapper);

        return page;
    }

    @Override
    public Page<SchoolInfo> getSchoolInfoList(Page<SchoolInfo> page, TotalSchoolDto schoolDto) {

        LambdaQueryWrapper<SchoolInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(schoolDto.getSchoolName())) {
            lambdaQueryWrapper.like(SchoolInfo::getSchoolName, schoolDto.getSchoolName());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getProvinceId())) {
            lambdaQueryWrapper.in(SchoolInfo::getProvinceId, schoolDto.getProvinceId());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getSchoolTypeId())) {
            lambdaQueryWrapper.in(SchoolInfo::getSchoolTypeId, schoolDto.getSchoolTypeId());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getSchoolFeatureId())) {
            List<Long> schoolListId = schoolLevelMapper.selectList(
                    Wrappers.lambdaQuery(SchoolLevel.class)
                            .in(SchoolLevel::getSchoolFeatureId, schoolDto.getSchoolFeatureId())
            ).stream().map(SchoolLevel::getSchoolId).collect(Collectors.toList());
            lambdaQueryWrapper.in(SchoolInfo::getSchoolId, schoolListId);
        }

        schoolInfoMapper.selectPage(page, lambdaQueryWrapper);


        page.getRecords().forEach(school -> {
            school.setSchoolFeatureIds(
                    schoolLevelMapper.selectList(
                            Wrappers.lambdaQuery(SchoolLevel.class)
                                    .eq(SchoolLevel::getSchoolId, school.getSchoolId())
                    ).stream().map(SchoolLevel::getSchoolFeatureId).collect(Collectors.toList())
            );
        });

        return page;
    }
}




