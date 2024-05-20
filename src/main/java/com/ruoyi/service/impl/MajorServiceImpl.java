package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.domain.vo.TotalMajorVo;
import com.ruoyi.service.MajorService;
import com.ruoyi.mapper.MajorMapper;
import com.ruoyi.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author ASUS
* @description 针对表【major】的数据库操作Service实现
* @createDate 2024-05-06 16:28:24
*/
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
    implements MajorService{
    @Override
    public List<TotalMajorVo> getMajorVoList(List<Major> majors){
        List<TotalMajorVo> totalMajorVos = new ArrayList<>();
        for (Major major : majors) {
            TotalMajorVo totalMajorVo=new TotalMajorVo();
            if (major.getMajorCode().length()==6||major.getMajorCode().equals("自定义")){
                totalMajorVo.setMajorId(major.getMajorId());
                totalMajorVo.setMajorName(major.getMajorName());
                totalMajorVo.setMajorCode(major.getMajorCode());
                totalMajorVo.setDegreeName(major.getDegreeName());
                Major subDiscipline = majors.stream()
                        .filter(m -> m.getMajorId().equals(major.getParentMajorId()))
                        .findFirst()
                        .orElse(null);
                totalMajorVo.setSubDiscipline(subDiscipline.getMajorName());
                Major firstLevelDiscipline=majors.stream()
                        .filter(m -> m.getMajorId().equals(subDiscipline.getParentMajorId()))
                        .findFirst()
                        .orElse(null);
                totalMajorVo.setFirstLevelDiscipline(firstLevelDiscipline.getMajorName());
                totalMajorVos.add(totalMajorVo);
            }
        }
        return totalMajorVos;
    }
}




