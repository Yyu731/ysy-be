package com.ruoyi.web.yxy;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.service.SchoolFeatureService;
import com.ruoyi.service.SchoolProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: YxyProvinceController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-24 20:27
 * @Version 1.0
 */
@RestController
@RequestMapping("yxy/province")
public class YxyProvinceController extends BaseController {

    @Autowired
    private SchoolProvinceService schoolProvinceService;

    @PreAuthorize("hasAuthority('yxy:school:list')")
    @GetMapping("/list")
    public AjaxResult list() {
        return success(schoolProvinceService.list());
    }

}
