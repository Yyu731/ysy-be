package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.*;
import com.ruoyi.dto.TotalProductDto;
import com.ruoyi.dto.TotalSchoolDto;
import com.ruoyi.service.ProductService;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("customer/shop")
public class ShopController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/totallist")
    public TableDataInfo totallist() {
        Page<Product> page = getPage();
        productService.page(page);
        //10条记录里
        List<Product> records = page.getRecords();

        List<ProductVo> totalSchoolVoList = productService.getTotalProductList(records);
        return getDataTable(totalSchoolVoList);
    }

//    @GetMapping("/detaillist")
//    public TableDataInfo detaillist() {
//        Page<Product> page = getPage();
//        productService.page(page);
//        //10条记录里
//        List<Product> records = page.getRecords();
//
//        List<DetailProductVo> detailProductVoList = productService.getDetailProductList(records);
//        return getDataTable(detailProductVoList);
//    }
}