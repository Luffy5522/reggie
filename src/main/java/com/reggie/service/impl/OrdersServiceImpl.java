package com.reggie.service.impl;

import com.reggie.pojo.Orders;
import com.reggie.mapper.OrdersMapper;
import com.reggie.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author luffy
 * @since 2023-02-28
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

}
