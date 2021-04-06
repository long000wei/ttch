package com.imooc.service;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;

/**
 * @author 龙伟
 * @Description:
 * @date 2021/3/28 18:00
 */
public interface OrderService {

    //创建订单
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

}
