package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.RedisOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 龙伟
 * @Description:
 * @date 2020/12/13 17:43
 */
@Api(value = "首页",tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisOperator redisOperator;

    @ApiOperation(value = "轮播图",notes = "获取轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carouse(){

        List<Carousel> list = new ArrayList<>();
        String carouseStr = redisOperator.get("carouse");
        if (StringUtils.isBlank(carouseStr)) {
            list = carouselService.queryAll(YesOrNo.YES.type);
            redisOperator.set("carousel", JsonUtils.objectToJson(list));
        } else {
            list = JsonUtils.jsonToList(carouseStr,Carousel.class);
        }

        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){

        List<Category> list = categoryService.queryAllRootLevelCat();

        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable("rootCatId") Integer rootCatId){

        if (rootCatId == null) return IMOOCJSONResult.errorMsg("分类不存在");

        List<CategoryVO> list = new ArrayList<>();
        String catsStr = redisOperator.get("subCat:" + rootCatId);
        if (StringUtils.isBlank(catsStr)) {
            list = categoryService.getSubCatList(rootCatId);
            redisOperator.set("subCat:"+rootCatId,JsonUtils.objectToJson(list));
        }else {
            list = JsonUtils.jsonToList(catsStr,CategoryVO.class)
        }


        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "查询各个一级分类下的6个最新商品",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public IMOOCJSONResult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable("rootCatId") Integer rootCatId){

        if (rootCatId == null) return IMOOCJSONResult.errorMsg("分类不存在");

        List<NewItemsVO> list = categoryService.getSixNewItemsLazy(rootCatId);

        return IMOOCJSONResult.ok(list);
    }
}
