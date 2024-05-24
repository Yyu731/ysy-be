package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SchoolController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-24 19:43
 * @Version 1.0
 */
@RestController
@RequestMapping("yxy/school")
public class YxySchoolController extends BaseController {

    @Autowired
    private SchoolInfoService schoolInfoService;

    @PreAuthorize("hasAuthority('yxy:school:list')")
    @GetMapping("/list")
    public TableDataInfo list(TotalSchoolDto schoolDto) {
        Page<SchoolInfo> page = getPage();
        schoolInfoService.getSchoolInfoList(page, schoolDto);
        return getDataTable(page);
    }
}
