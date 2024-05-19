package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.HotSchoolMajorVo;
import com.ruoyi.domain.vo.TotalMajorVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.service.MajorService;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("major")
public class MajorController extends BaseController {

    @Autowired
    private SchoolInfoService schoolInfoService;
    @Autowired
    private MajorService majorService;
//    @GetMapping("/hotlist")
//    public TableDataInfo hotlist()
//    {
//        Page<Major> page = getPage();
//        System.out.println(page.toString());
//        majorService.page(page);
//        //10条记录里
//        List<Major> records = page.getRecords();
//
//        List<HotSchoolMajorVo> hotSchoolMajorVoList = majorService.g(records);
//        return getDataTable(briefSchoolVoList);
//    }
    @GetMapping("/totallist")
    public TableDataInfo totallist()
    {
        Page<Major> page = getPage();
        System.out.println(page.toString());
        majorService.page(page);
        List<Major> records = page.getRecords();
        List<TotalMajorVo> totalMajorVoList =majorService.getMajorVoList(records);
        return getDataTable(totalMajorVoList);
    }


}
