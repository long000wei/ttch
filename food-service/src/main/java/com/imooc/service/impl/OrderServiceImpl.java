package com.imooc.service.impl;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.vo.OrderVO;
import com.imooc.service.AddressService;
import com.imooc.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 龙伟
 * @Description:
 * @date 2021/3/28 18:02
 */
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Sid sid;

    @Autowired
    private AddressService addressService;

    @Override
    public OrderVO createOrder(SubmitOrderBO submitOrderBO) {

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();

        Integer postAmount = 0;

        String orderId = sid.nextShort();

        UserAddress address = addressService.queryUserAddress(userId, addressId);



        return null;
    }
}
