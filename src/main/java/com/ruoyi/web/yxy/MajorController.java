package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.SchoolMajor;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.domain.vo.TotalMajorVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.service.MajorService;
import com.ruoyi.service.SchoolInfoService;
import com.ruoyi.service.SchoolMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("yxy/major")
public class MajorController extends BaseController {

    @Autowired
    private SchoolMajorService schoolMajorService;
    @Autowired
    private MajorService majorService;
    @GetMapping("/hotlist")
    public TableDataInfo hotlist()
    {
        Page<SchoolMajor> page = getPage();
        schoolMajorService.page(page);
        int[] ids = IntStream.rangeClosed(1, 10).toArray();
//        schoolMajorService.page(page, Wrappers.lambdaQuery(SchoolMajor.class).in(SchoolMajor::getRelationId, ids));
//        schoolMajorService.page(page, Wrappers.lambdaQuery(SchoolMajor.class).in(SchoolMajor::getRelationId,1,2,3,4,5,6,7,8,9,10));
        //10条记录里
        List<SchoolMajor> records = page.getRecords();
//        int num=0;
//        for (SchoolMajor schoolMajor:records){
//            num++;
//            System.out.println("aaa"+num);
//            System.out.println(schoolMajor.getSchoolName());
//
//        }
        List<HotSchoolMajorVo> hotSchoolMajorVoList = schoolMajorService.getHotSchoolMajorVoList(records);
        return getDataTable(hotSchoolMajorVoList);
    }
    @GetMapping("/totallist")
    public TableDataInfo totallist()
    {
        Page<Major> page = getPage();
        majorService.page(page);
        List<Major> records = page.getRecords();
        List<TotalMajorVo> totalMajorVoList =majorService.getMajorVoList(records);
        return getDataTable(totalMajorVoList);
    }


}
