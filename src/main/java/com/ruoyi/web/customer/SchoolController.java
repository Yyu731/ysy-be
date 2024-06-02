package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.BriefSchoolVo;
import com.ruoyi.domain.vo.DetailSchoolVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer/school")
public class SchoolController extends BaseController {

    @Autowired
    private SchoolInfoService schoolInfoService;

    @GetMapping("/hotlist")
    public TableDataInfo hotlist() {
        Page<SchoolInfo> page = getPage();
        System.out.println(page.toString());
        schoolInfoService.page(page, Wrappers.lambdaQuery(SchoolInfo.class)
                .eq(SchoolInfo::getSchoolHot, 1));

        //10条记录里
        List<SchoolInfo> records = page.getRecords();
        List<BriefSchoolVo> briefSchoolVoList = schoolInfoService.getBriefSchoolList(records);
        return getDataTable(briefSchoolVoList);
    }

    @GetMapping("/totallist")
    public TableDataInfo totallist(TotalSchoolDto totalSchoolDto) {
        Page<SchoolInfo> page = getPage();
        schoolInfoService.getSchoolInfoList(page, totalSchoolDto);
        //10条记录里
        List<SchoolInfo> records = page.getRecords();

        List<TotalSchoolVo> totalSchoolVoList = schoolInfoService.getTotalSchoolList(records);
        return getDataTable(totalSchoolVoList);
    }

    @GetMapping("/detail")
    public AjaxResult detaillist(SchoolInfo schoolInfo) {
       return success(schoolInfoService.getDetailSchoolVo(schoolInfoService.getById(schoolInfo.getSchoolId())));
    }
}
