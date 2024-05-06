package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("school")
public class SchoolController extends BaseController {

    @Autowired
    private SchoolInfoService schoolInfoService;
    @GetMapping("/list")
    public TableDataInfo list()
    {
        Page<SchoolInfo> page = getPage();
        schoolInfoService.page(page);
        return getDataTable(page);
    }
}
