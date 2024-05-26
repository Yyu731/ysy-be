package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @PreAuthorize("hasAuthority('yxy:school:add')")
    @PostMapping
    public AjaxResult add(@RequestBody SchoolInfo schoolInfo) {
        return toAjax(schoolInfoService.insertSchoolInfo(schoolInfo));
    }

    /**
     * 修改个人信息
     */
    @PreAuthorize("hasAuthority('yxy:school:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody SchoolInfo schoolInfo) {
        return toAjax(schoolInfoService.updateSchoolInfo(schoolInfo));
    }

    /**
     * 删除个人信息
     */
    @PreAuthorize("hasAuthority('yxy:school:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(schoolInfoService.deleteUserInfoByIds(ids));
    }

}
