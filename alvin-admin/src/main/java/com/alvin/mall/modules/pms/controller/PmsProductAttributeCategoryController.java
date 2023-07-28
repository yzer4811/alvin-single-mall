package com.alvin.mall.modules.pms.controller;


import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.pms.dto.PmsProductAttributeCategoryDTO;
import com.alvin.mall.modules.pms.model.PmsProductAttributeCategory;
import com.alvin.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    PmsProductAttributeCategoryService productAttributeCategoryService;

    /**
     * url:'/productAttribute/category/list',
     *     method:'get',
     *     params:params
     */
    @RequestMapping(value = "/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize")Integer pageSize) {

        Page<PmsProductAttributeCategory> page = productAttributeCategoryService.list(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/productAttribute/category/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("根据ID更新")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateProductAttrCate(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name) {

        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProductAttributeCategory::getName, name)
                .eq(PmsProductAttributeCategory::getId, id);
        boolean res = productAttributeCategoryService.update(updateWrapper);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/category/delete/'+id,
     *     method:'get'
     * http://localhost:8099/productAttribute/category/delete/1
     */

    @ApiOperation("根据ID删除")
    @RequestMapping(value = "/delete/{id}")
    public CommonResult deleteProductAttrCate(@PathVariable("id") Integer id) {

        boolean res = productAttributeCategoryService.removeById(id);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/category/create',
     *     method:'post',
     *     data:data
     * http://localhost:8099/productAttribute/category/create
     */
    @ApiOperation("添加新商品属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult addProductAttrCate(@RequestParam("name") String name) {

        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory();
        productAttributeCategory.setName(name);
        productAttributeCategory.setAttributeCount(0);
        productAttributeCategory.setParamCount(0);
        boolean res = productAttributeCategoryService.save(productAttributeCategory);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 获取带有属性的商品类别列表
     * url:'/productAttribute/category/list/withAttr',
     *     method:'get'
     */
    @ApiOperation("获取筛选属性级联列表")
    @RequestMapping(value = "/list/withAttr", method = RequestMethod.GET)
    public CommonResult<List<PmsProductAttributeCategoryDTO>> getListWithAttr() {
        List<PmsProductAttributeCategoryDTO> listWithAttr = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(listWithAttr);
    }
}

