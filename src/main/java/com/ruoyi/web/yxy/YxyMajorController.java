package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: YxyMajorController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-26 16:21
 * @Version 1.0
 */
@RestController
@RequestMapping("yxy/major")
public class YxyMajorController extends BaseController {

    @Autowired
    private MajorService majorService;


    @PreAuthorize("hasAuthority('yxy:major:list')")
    @GetMapping("/list")
    public TableDataInfo list(Major major) {
        Page<Major> page = getPage();
//        majorService.getMajorList(page, major);
        return getDataTable(page);
    }

}
