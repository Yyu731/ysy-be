package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("school")
public class SchoolController extends BaseController {

    @Autowired
    private SchoolInfoService schoolInfoService;
    @GetMapping("/list")
    public TableDataInfo list()
    {
        Page<SchoolInfo> page = getPage();
        System.out.println(page.toString());
        schoolInfoService.page(page);
        //10条记录里
        List<SchoolInfo> records = page.getRecords();

        int num=0;
        for (SchoolInfo schoolInfo:records){
            num++;
            System.out.println("aaa"+num);
            System.out.println(schoolInfo.getSchoolName());

        }
        List<BriefSchoolVo> briefSchoolVoList = schoolInfoService.getBriefSchoolList(records);
        return getDataTable(briefSchoolVoList);
    }
}
