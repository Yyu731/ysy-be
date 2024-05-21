package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolLevel;
import com.ruoyi.domain.SchoolMajor;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.service.SchoolInfoService;
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
    @Autowired
    private SchoolInfoService schoolInfoService;
    @Override
    public List<HotSchoolMajorVo> getHotSchoolMajorVoList(List<SchoolMajor> schoolMajorList){
        List<HotSchoolMajorVo> hotSchoolMajorVos = new ArrayList<>();
        for (SchoolMajor schoolMajor : schoolMajorList) {
            HotSchoolMajorVo hotSchoolMajorVo = new HotSchoolMajorVo();
            hotSchoolMajorVo.setSchoolId(schoolMajor.getSchoolId());
            hotSchoolMajorVo.setSchoolName(schoolMajor.getSchoolName());
            hotSchoolMajorVo.setMajorName(schoolMajor.getMajorName());
            hotSchoolMajorVo.setMajorId(schoolMajor.getMajorId());
            SchoolInfo schoolInfo=schoolInfoService.getOne(Wrappers.lambdaQuery(SchoolInfo.class)
                            .eq(SchoolInfo::getSchoolId,schoolMajor.getSchoolId()));

            hotSchoolMajorVo.setSchoolBadge(schoolInfo.getSchoolBadge());
            hotSchoolMajorVos.add(hotSchoolMajorVo);
        }
        return hotSchoolMajorVos;
    }
}




