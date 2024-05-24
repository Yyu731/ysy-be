package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.vo.DetailProductVo;
import com.ruoyi.domain.vo.ProductVo;
import com.ruoyi.dto.TotalProductDto;
import com.ruoyi.service.ProductService;
import com.ruoyi.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author ASUS
* @description 针对表【product】的数据库操作Service实现
* @createDate 2024-05-25 01:03:09
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{


    public List<ProductVo> getTotalProductList(List<Product> productList){
        List<ProductVo> productVos=new ArrayList<>();
        return  productVos;
    }

    public List<DetailProductVo> getDetailProductList(List<Product> productList){
        List<DetailProductVo> detailProductVos=new ArrayList<>();
        return  detailProductVos;
    }

//    Page getPage(Page<SchoolInfo> page, TotalSchoolDto totalSchoolDto);

    public Page<Product> getProductList(Page<Product> page, TotalProductDto totalProductDto){
        return page;
    }
}




