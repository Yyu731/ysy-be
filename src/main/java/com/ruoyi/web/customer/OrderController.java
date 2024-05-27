package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.OrderTable;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.vo.OrderVo;
import com.ruoyi.domain.vo.ProductVo;
import com.ruoyi.service.OrderTableService;
import com.ruoyi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class OrderController  extends BaseController {

    @Autowired
    private OrderTableService orderTableService;

    @GetMapping("/totallist")
    public TableDataInfo totallist() {
        Page<OrderTable> page = getPage();
        orderTableService.page(page);
        //10条记录里
        List<OrderTable> records = page.getRecords();

        List<OrderVo> orderVoList = orderTableService.getOrderTableList(records);
        return getDataTable(orderVoList);
    }
}
