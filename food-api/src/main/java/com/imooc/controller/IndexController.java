package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation(value = "轮播图",notes = "获取轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public IMOOCJSONResult carouse(){

        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);

        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public IMOOCJSONResult cats(){

        List<Category> list = categoryService.queryAllRootLevelCat();

        return IMOOCJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类",notes = "获取商品子分类",httpMethod = "GET")
    @GetMapping("/subCats/{rootCatId}")
    public IMOOCJSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一级分类id",required = true)
            @PathVariable("rootCatId") Integer rootCatId){

        if (rootCatId == null) return IMOOCJSONResult.errorMsg("分类不存在");

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);

        return IMOOCJSONResult.ok(list);
    }
}
