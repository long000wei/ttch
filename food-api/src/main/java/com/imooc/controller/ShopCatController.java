package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙伟
 * @Description:
 * @date 2021/3/27 23:32
 */
@RestController
@RequestMapping("shopcart")
public class ShopCatController extends BaseController{

    @Autowired
    private RedisOperator redisOperator;

    @PostMapping("/add")
    public IMOOCJSONResult add(@RequestParam String userId, @RequestBody ShopcartBO shopcartBO) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);
        String shopcarJson = redisOperator.get(FOODIE_SHOPCART + ":" + userId);
        List<ShopcartBO> shopcartList = null;
        if (StringUtils.isNotBlank(shopcarJson)) {
            shopcartList = JsonUtils.jsonToList(shopcarJson,ShopcartBO.class);
            boolean isHaving = false;
            for (ShopcartBO sc : shopcartList) {
                String temSpecId = sc.getSpecId();
                if (temSpecId.equals(shopcartBO.getSpecId())) {
                    sc.setBuyCounts(sc.getBuyCounts()+shopcartBO.getBuyCounts());
                    isHaving = true;
                }
            }
            if (!isHaving) {
                shopcartList.add(shopcartBO);
            }
        }else {
            shopcartList = new ArrayList<>();
            shopcartList.add(shopcartBO);
        }

        redisOperator.set(FOODIE_SHOPCART+":",JsonUtils.objectToJson(shopcartList));

        return IMOOCJSONResult.ok();
    }

    @PostMapping("/del")
    public IMOOCJSONResult del(@RequestParam String userId, @RequestParam String itemSpecId) {

        if (StringUtils.isBlank(userId)) {
            return IMOOCJSONResult.errorMsg("参数不能为空");
        }
        String shopcarJson = redisOperator.get(FOODIE_SHOPCART);
        if (StringUtils.isNotBlank(shopcarJson)) {
            List<ShopcartBO> shopcartList = JsonUtils.jsonToList(shopcarJson,ShopcartBO.class);
            for (ShopcartBO sc : shopcartList) {
                String tmpSpecId = sc.getSpecId();
                if (tmpSpecId.equals(itemSpecId)) {
                    shopcartList.remove(sc);
                    break;
                }
            }
            redisOperator.set(FOODIE_SHOPCART+":",JsonUtils.objectToJson(shopcartList));
        }

        return IMOOCJSONResult.ok();
    }

}
