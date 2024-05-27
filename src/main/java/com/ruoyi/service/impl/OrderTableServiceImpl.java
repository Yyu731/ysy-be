package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.OrderTable;
import com.ruoyi.domain.Product;
import com.ruoyi.domain.vo.OrderVo;
import com.ruoyi.domain.vo.ProductVo;
import com.ruoyi.service.OrderTableService;
import com.ruoyi.mapper.OrderTableMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author ASUS
* @description 针对表【order_table】的数据库操作Service实现
* @createDate 2024-05-27 12:05:00
*/
@Service
public class OrderTableServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable>
    implements OrderTableService{
    public List<OrderVo> getOrderTableList(List<OrderTable> orderTableList){
        List<OrderVo> orderVoList=new ArrayList<>();
        for (OrderTable orderTable:orderTableList){
            OrderVo orderVo=new OrderVo();
            orderVo.setRelationId(orderTable.getRelationId());
            orderVo.setProductId(orderTable.getProductId());
            orderVo.setOrderNumber(orderTable.getOrderNumber());
            orderVo.setOrderTime(orderTable.getOrderTime());
            orderVo.setPaymentAmount(orderTable.getPaymentAmount());
            orderVo.setUserId(orderTable.getUserId());
            orderVoList.add(orderVo);
        }

        return  orderVoList;
    }
}




