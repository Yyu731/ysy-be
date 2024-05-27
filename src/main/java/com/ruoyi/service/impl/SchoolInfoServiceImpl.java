package com.ruoyi.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.domain.*;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.DetailSchoolVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.mapper.SchoolFeatureMapper;
import com.ruoyi.mapper.SchoolInfoMapper;
import com.ruoyi.mapper.SchoolLevelMapper;
import com.ruoyi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    private RankingService rankingService;
    @Autowired
    private SchoolPicService schoolPicService;

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

//        for (TotalSchoolVo totalSchoolVo : totalSchoolVos) {
//            System.out.println(totalSchoolVo.getSchoolName());
//        }
        return totalSchoolVos;
    }

    @Override
    public List<DetailSchoolVo> getDetailSchoolList(List<SchoolInfo> schoolInfList) {
        List<DetailSchoolVo> detailSchoolVos = new ArrayList<>();
        for (SchoolInfo schoolInfo : schoolInfList) {
            DetailSchoolVo detailSchoolVo = new DetailSchoolVo();
            detailSchoolVo.setSchoolId(schoolInfo.getSchoolId());
            detailSchoolVo.setSchoolName(schoolInfo.getSchoolName());
            detailSchoolVo.setSchoolBadge(schoolInfo.getSchoolBadge());
            detailSchoolVo.setProvince(schoolProvinceService.getById(schoolInfo.getProvinceId()).getRegionName());
            detailSchoolVo.setSchoolType(schoolTypeService.getById(schoolInfo.getSchoolTypeId()).getTypeName());
            detailSchoolVo.setIntro(schoolInfo.getIntro());
            detailSchoolVo.setSchoolAddress(schoolInfo.getSchoolAddress());
            detailSchoolVo.setCreateDate(schoolInfo.getCreateDate());
            detailSchoolVo.setSchoolEmail(schoolInfo.getSchoolEmail());
            detailSchoolVo.setSchoolPhone(schoolInfo.getSchoolPhone());
            detailSchoolVo.setSchoolSite(schoolInfo.getSchoolSite());
            detailSchoolVo.setSchoolSpace(schoolInfo.getSchoolSpace());
            detailSchoolVo.setNumMaster(schoolInfo.getNumMaster());
            detailSchoolVo.setNumDoctor(schoolInfo.getNumDoctor());
            detailSchoolVo.setNumLab(schoolInfo.getNumLab());
            detailSchoolVo.setNumSubject(schoolInfo.getNumSubject());
//            detailSchoolVo.setQSrank(rankingService.getById(schoolInfo.getProvinceId()).getRanking());
            Ranking ranking = rankingService.getOne(Wrappers.<Ranking>lambdaQuery()
                    .eq(Ranking::getSchoolId, schoolInfo.getSchoolId()));
            if (ranking != null) {
                detailSchoolVo.setQSrank(ranking.getRanking());
            } else {
                detailSchoolVo.setQSrank("未排名");
            }
            List<SchoolLevel> schoolLevel = schoolLevelService.list(Wrappers.<SchoolLevel>lambdaQuery()
                    .in(SchoolLevel::getSchoolFeatureId, 5, 6)
                    .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId())
            );
            for (SchoolLevel schoolLevel1 : schoolLevel) {
                detailSchoolVo.setTypeFeatures(schoolLevel1.getFeatureName());
            }
            detailSchoolVo.setFeatures(
                    schoolLevelService.list(
                                    Wrappers.lambdaQuery(SchoolLevel.class)
                                            .in(SchoolLevel::getSchoolFeatureId, 1, 2, 3, 4)
                                            .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
                            .stream().map(item -> item.getFeatureName()).limit(3)
                            .collect(Collectors.toList()));
            detailSchoolVo.setSchoolPic(schoolPicService.list(
                            Wrappers.lambdaQuery(SchoolPic.class)
                                    .eq(SchoolPic::getSchoolId, schoolInfo.getSchoolId()))
                    .stream().map(item -> item.getImageUrl())
                    .collect(Collectors.toList())
            );
            detailSchoolVos.add(detailSchoolVo);
        }

        return detailSchoolVos;
    }
//    @Override
//    public Page getPage(Page<SchoolInfo> page, TotalSchoolDto totalSchoolDto) {
//
//        LambdaQueryWrapper<SchoolInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        if (StrUtil.isNotEmpty(totalSchoolDto.getSchoolName())) {
//            lambdaQueryWrapper.like(SchoolInfo::getSchoolName, totalSchoolDto.getSchoolName());
//        }
//
//        schoolInfoMapper.selectPage(page, lambdaQueryWrapper);
//
//        return page;
//    }

    @Override
    public Page<SchoolInfo> getSchoolInfoList(Page<SchoolInfo> page, TotalSchoolDto schoolDto) {

        LambdaQueryWrapper<SchoolInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(schoolDto.getSchoolName())) {
            lambdaQueryWrapper.like(SchoolInfo::getSchoolName, schoolDto.getSchoolName());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getProvinceIds())) {
            lambdaQueryWrapper.in(SchoolInfo::getProvinceId, schoolDto.getProvinceIds());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getSchoolTypeIds())) {
            lambdaQueryWrapper.in(SchoolInfo::getSchoolTypeId, schoolDto.getSchoolTypeIds());
        }

        if (CollectionUtil.isNotEmpty(schoolDto.getSchoolFeatureIds())) {
            List<Integer> schoolListId = schoolLevelMapper.selectList(
                    Wrappers.lambdaQuery(SchoolLevel.class)
                            .in(SchoolLevel::getSchoolFeatureId, schoolDto.getSchoolFeatureIds())
            ).stream().map(SchoolLevel::getSchoolId).collect(Collectors.toList());
            lambdaQueryWrapper.in(SchoolInfo::getSchoolId, schoolListId);
        }

        if (ObjectUtil.isNotEmpty(schoolDto.getSchoolHot())) {
            lambdaQueryWrapper.eq(SchoolInfo::getSchoolHot, schoolDto.getSchoolHot());
        }

        lambdaQueryWrapper.orderByDesc(SchoolInfo::getSchoolHot);
        lambdaQueryWrapper.orderByAsc(SchoolInfo::getSchoolId);

        schoolInfoMapper.selectPage(page, lambdaQueryWrapper);


        page.getRecords().forEach(school -> {
            school.setSchoolFeatureIds(
                    schoolLevelMapper.selectList(
                            Wrappers.lambdaQuery(SchoolLevel.class)
                                    .eq(SchoolLevel::getSchoolId, school.getSchoolId())
                                    .orderByAsc(SchoolLevel::getSchoolFeatureId)
                    ).stream().map(SchoolLevel::getSchoolFeatureId).collect(Collectors.toList())
            );
        });

        return page;
    }

    @Override
    public int insertSchoolInfo(SchoolInfo schoolInfo) {
        schoolInfo.setCreateTime(DateUtil.date());
        schoolInfo.setCreateBy(SecurityUtils.getUsername());
        int res =  schoolInfoMapper.insert(schoolInfo);

        for (Integer schoolFeatureId : schoolInfo.getSchoolFeatureIds()) {
            SchoolLevel schoolLevel = new SchoolLevel();
            schoolLevel.setSchoolFeatureId(schoolFeatureId);
            schoolLevel.setFeatureName(
                    schoolFeatureMapper.selectOne(
                            Wrappers.lambdaQuery(SchoolFeature.class)
                                    .eq(SchoolFeature::getSchoolFeatureId, schoolFeatureId)
                    ).getFeatureName());
            schoolLevel.setSchoolId(schoolInfo.getSchoolId());
            schoolLevel.setSchoolName(schoolInfo.getSchoolName());
            schoolLevelMapper.insert(schoolLevel);
        }

        return res;
    }

    @Override
    public int updateSchoolInfo(SchoolInfo schoolInfo) {

        schoolLevelMapper.delete(
                Wrappers.lambdaQuery(SchoolLevel.class)
                        .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId())
        );

        for (Integer schoolFeatureId : schoolInfo.getSchoolFeatureIds()) {
            SchoolLevel schoolLevel = new SchoolLevel();
            schoolLevel.setSchoolFeatureId(schoolFeatureId);
            schoolLevel.setFeatureName(
                    schoolFeatureMapper.selectOne(
                            Wrappers.lambdaQuery(SchoolFeature.class)
                                    .eq(SchoolFeature::getSchoolFeatureId, schoolFeatureId)
                    ).getFeatureName());
            schoolLevel.setSchoolId(schoolInfo.getSchoolId());
            schoolLevel.setSchoolName(schoolInfo.getSchoolName());
            schoolLevelMapper.insert(schoolLevel);
        }

        schoolInfo.setUpdateTime(DateUtil.date());
        schoolInfo.setUpdateBy(SecurityUtils.getUsername());
        return schoolInfoMapper.updateById(schoolInfo);
    }

    @Override
    public int deleteUserInfoByIds(Integer[] ids) {

        for (Integer id : ids) {
            schoolLevelMapper.delete(
                    Wrappers.lambdaQuery(SchoolLevel.class)
                            .eq(SchoolLevel::getSchoolId, id)
            );
        }

        return schoolInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }
}




