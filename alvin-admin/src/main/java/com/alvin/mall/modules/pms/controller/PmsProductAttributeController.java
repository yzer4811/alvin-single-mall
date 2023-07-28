package com.alvin.mall.modules.pms.controller;


import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryAttributeRelationDTO;
import com.alvin.mall.modules.pms.model.PmsProductAttribute;
import com.alvin.mall.modules.pms.service.PmsProductAttributeService;
import com.alvin.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService productAttributeService;

    @Autowired
    PmsProductCategoryAttributeRelationService productCategoryAttributeRelationService;


    /**
     * http://localhost:8099/productAttribute/list/1?pageNum=1&pageSize=5&type=0
     */
    @ApiOperation("获取属性列表")
    @RequestMapping(value = "/list/{cid}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttribute>> getList(
            @PathVariable Integer cid,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("type") Integer type) {

        Page<PmsProductAttribute> page = productAttributeService.list(cid, pageNum, pageSize, type);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/productAttribute/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加新商品属性")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createProductAttr(@RequestBody PmsProductAttribute productAttribute) {
        boolean res = productAttributeService.save(productAttribute);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * return request({
     *     url:'/productAttribute/'+id,
     *     method:'get'
     *   })
     */
    @ApiOperation("根据ID获取商品属性")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductAttribute> getProductAttr(@PathVariable("id") Integer id) {

        PmsProductAttribute productAttribute = productAttributeService.getById(id);
        return CommonResult.success(productAttribute);
    }

    /**
     * url:'/productAttribute/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("根据ID修改商品属性")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateProductAttr(
            @PathVariable("id") Integer id,
            @RequestBody PmsProductAttribute productAttribute) {

        boolean res = productAttributeService.updateById(productAttribute);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/delete',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult deleteProductAttr(@RequestParam("ids") List<Long> ids) {

        boolean res = productAttributeService.delete(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     *     method:'get'
     */
    @RequestMapping(value = "/attrInfo/{productCategoryId}")
    public CommonResult<List<PmsProductCategoryAttributeRelationDTO>> getProductAttrInfo(
            @PathVariable("productCategoryId") Long productCategoryId) {

        List<PmsProductCategoryAttributeRelationDTO> productAttrInfo = productCategoryAttributeRelationService.getProductAttrInfo(productCategoryId);
        System.out.println(productAttrInfo);
        return CommonResult.success(productAttrInfo);
    }
}

