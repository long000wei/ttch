package com.imooc.controller;




import com.imooc.enums.PayMethod;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 龙伟
 * @Description:
 * @date 2021/3/27 23:26
 */
@Api(value = "订单相关",tags = {"订单相关的api接口"})
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    //用户下单
    @ApiOperation(value = "用户下单",tags = "用户下单了",httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderBO submitOrderBO) {

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type &&
                submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type) {
            return IMOOCJSONResult.errorMsg("支付方式不支持");
        }
        //1 创建订单

        //2 移除购物车中已结算的商品

        //3 向支付中心发送当前订单，用于保持支付中心的订单数据
        return null;
    }





}
