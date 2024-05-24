package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.DetailProductVo;
import com.ruoyi.domain.vo.DetailSchoolVo;
import com.ruoyi.domain.vo.ProductVo;
import com.ruoyi.domain.vo.TotalSchoolVo;
import com.ruoyi.dto.TotalProductDto;
import com.ruoyi.dto.TotalSchoolDto;

import java.util.List;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Service
* @createDate 2024-05-25 01:03:09
*/
public interface ProductService extends IService<Product> {
    List<ProductVo> getTotalProductList(List<Product> productList);

    List<DetailProductVo> getDetailProductList(List<Product> productList);

//    Page getPage(Page<SchoolInfo> page, TotalSchoolDto totalSchoolDto);

    Page<Product> getProductList(Page<Product> page, TotalProductDto totalProductDto);
}
