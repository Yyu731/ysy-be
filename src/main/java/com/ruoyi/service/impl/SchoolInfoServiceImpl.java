package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolLevel;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.mapper.SchoolInfoMapper;
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

    @Override
    public List<BriefSchoolVo> getBriefSchoolList(List<SchoolInfo> schoolInfList) {

        List<BriefSchoolVo> briefSchoolVos = new ArrayList<>();
        int num=0;
        for (SchoolInfo schoolInfo : schoolInfList) {
            num++;
            if(schoolInfo.getSchoolHot()==1) {
                BriefSchoolVo briefSchoolVo = new BriefSchoolVo();
                briefSchoolVo.setSchoolId(schoolInfo.getSchoolId());
                briefSchoolVo.setSchoolName(schoolInfo.getSchoolName());
                briefSchoolVo.setSchoolGate(schoolInfo.getSchoolGate());
                briefSchoolVo.setProvince(schoolProvinceService.getById(schoolInfo.getProvinceId()).getRegionName());
                briefSchoolVo.setSchoolType(schoolTypeService.getById(schoolInfo.getSchoolTypeId()).getTypeName());
                briefSchoolVo.setFeatures(
                        schoolLevelService.list(
                                        Wrappers.lambdaQuery(SchoolLevel.class)
                                                .in(SchoolLevel::getSchoolFeatureId, 1,2,3,4)
                                                .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
                                .stream().map(item -> item.getFeatureName()).limit(3)
                                .collect(Collectors.toList()));
                briefSchoolVos.add(briefSchoolVo);
            }
            System.out.println(num);
        }
        for (BriefSchoolVo briefSchoolVo: briefSchoolVos){
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
            for (SchoolLevel schoolLevel1:schoolLevel) {
                totalSchoolVo.setTypeFeatures(schoolLevel1.getFeatureName());
            }
                totalSchoolVo.setFeatures(
                        schoolLevelService.list(
                                        Wrappers.lambdaQuery(SchoolLevel.class)
                                                .in(SchoolLevel::getSchoolFeatureId, 1,2,3,4)
                                                .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
                                .stream().map(item -> item.getFeatureName()).limit(3)
                                .collect(Collectors.toList()));
                totalSchoolVos.add(totalSchoolVo);
        }

        for (TotalSchoolVo totalSchoolVo: totalSchoolVos){
            System.out.println(totalSchoolVo.getSchoolName());
        }
        return totalSchoolVos;
    }
}




