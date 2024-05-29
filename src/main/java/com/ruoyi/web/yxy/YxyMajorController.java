package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.MajorPrimary;
import com.ruoyi.mapper.MajorMapper;
import com.ruoyi.mapper.MajorPrimaryMapper;
import com.ruoyi.service.MajorPrimaryService;
import com.ruoyi.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private MajorPrimaryService majorPrimaryService;

    @Autowired
    private MajorService majorService;
    @Autowired
    private MajorMapper majorMapper;


    @PreAuthorize("hasAuthority('yxy:major:list')")
    @GetMapping("/firstlist")
    public AjaxResult list(MajorPrimary majorPrimary) {
        return success(majorPrimaryService.selectMajorPrimaryList());
    }

    @PreAuthorize("hasAuthority('yxy:major:list')")
    @GetMapping("/secondayMajor/{id}")
    public AjaxResult list(@PathVariable Integer id) {
        return success(majorPrimaryService.selectMajorSecondayPrimaryListByPrimaryId(id));
    }

    @PreAuthorize("hasAuthority('yxy:major:list')")
    @GetMapping("/majorList")
    public TableDataInfo list(Major major) {
        Page<Major> page = getPage();
        majorService.selectMajorListBySecondaryId(page ,major.getParentId());
        return getDataTable(page);
    }

    @PreAuthorize("hasAuthority('yxy:major:remove')")
    @DeleteMapping("{id}")
    public AjaxResult remove(@PathVariable Integer id) {
        return success(majorMapper.deleteById(id));
    }

}
