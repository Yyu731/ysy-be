package com.ruoyi.service;

import com.ruoyi.domain.OrderTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.vo.OrderVo;
import com.ruoyi.domain.vo.ProductVo;

import java.util.List;

/**
* @author ASUS
* @description 针对表【order_table】的数据库操作Service
* @createDate 2024-05-27 12:05:00
*/
public interface OrderTableService extends IService<OrderTable> {
    List<OrderVo> getOrderTableList(List<OrderTable> orderTableList);
}
