package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolLevel;
import com.ruoyi.domain.SchoolMajor;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.service.SchoolMajorService;
import com.ruoyi.mapper.SchoolMajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author ASUS
* @description 针对表【school_major】的数据库操作Service实现
* @createDate 2024-05-06 16:28:24
*/
@Service
public class SchoolMajorServiceImpl extends ServiceImpl<SchoolMajorMapper, SchoolMajor>
    implements SchoolMajorService{
    @Override
    public List<HotSchoolMajorVo> getHotSchoolMajorVoList(List<SchoolInfo> schoolInfList){
        List<HotSchoolMajorVo> hotSchoolMajorVos = new ArrayList<>();
        for (SchoolInfo schoolInfo : schoolInfList) {
//            if(schoolInfo.getSchoolHot()==1) {
                HotSchoolMajorVo hotSchoolMajorVo = new HotSchoolMajorVo();
            hotSchoolMajorVo.setSchoolId(schoolInfo.getSchoolId());
            hotSchoolMajorVo.setSchoolName(schoolInfo.getSchoolName());
            hotSchoolMajorVo.setSchoolBadge(schoolInfo.getSchoolGate());
//            hotSchoolMajorVo.setMajorName(
//                    Wrappers.lambdaQuery(SchoolMajor.class)
//                            .equals(S)
//            );
//            hotSchoolMajorVo.
//                h.setFeatures(
//                        schoolLevelService.list(
//                                        Wrappers.lambdaQuery(SchoolLevel.class)
//                                                .in(SchoolLevel::getSchoolFeatureId, 1,2,3,4)
//                                                .eq(SchoolLevel::getSchoolId, schoolInfo.getSchoolId()))
//                                .stream().map(item -> item.getFeatureName()).limit(3)
//                                .collect(Collectors.toList()));
//                hotSchoolMajorVos.add(hotSchoolMajorVo);
//            }

        }
        return hotSchoolMajorVos;
    }
}




