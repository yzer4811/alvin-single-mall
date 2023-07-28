package com.alvin.mall.modules.pms.controller;


import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryDTO;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryWithChildrenDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.alvin.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@RestController
@Api(tags = "PmsProductCategoryController", description = "后台商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryService productCategoryService;

    /**
     * 前端请求数据格式
     * url:'/productCategory/list/'+parentId,
     *     method:'get',
     *     params:{
     *           pageNum: 1,
     *           pageSize: 5
     *         },
     * http://localhost:8099/productCategory/list/0?pageNum=1&pageSize=5
     */
    @ApiOperation("商品分类列表")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductCategory>> getList(
            @PathVariable Long parentId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        Page<PmsProductCategory> page = productCategoryService.list(parentId, pageNum, pageSize);
        System.out.println(page + Thread.currentThread().toString());
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/productCategory/update/navStatus',
     *     method:'post',
     *     data:{
     *          ids: [], //数组
     *          navStatus: row.navStatus //状态值
     *     }
     * http://localhost:8099/productCategory/update/navStatus
     */

    @ApiOperation("修改导航栏显示状态")
    @RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
    public CommonResult updateNavaStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("navStatus") Integer navStatus) {

        boolean res = productCategoryService.updateNavStatus(ids, navStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("showStatus") Integer showStatus) {

        boolean res = productCategoryService.updateShowStatus(ids, showStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 删除商品种类
     * url:'/productCategory/delete/'+id,
     *     method:'post'
     */

    @ApiOperation("删除商品类别")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult deleteProductCate(@PathVariable("id") Long id) {

        boolean res = productCategoryService.removeById(id);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 添加商品类别
     * url:'/productCategory/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加商品类别")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createCategory(@RequestBody PmsProductCategoryDTO productCategory) {
        boolean res = productCategoryService.add(productCategory);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     *
     * url:'/productCategory/'+id,
     *     method:'get',
     */
    @ApiOperation("根据ID查商品类别")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductCategory> getProductCategoryById(@PathVariable("id") Integer id) {

        PmsProductCategory res = productCategoryService.getById(id);
        return CommonResult.success(res);
    }

    /**
     * url:'/productCategory/update/'+id,
     *     method:'post',
     *     data:data
     */
    @ApiOperation("根据ID更新商品类别")
    @RequestMapping(value = "/update/{id}")
    public CommonResult updateProductCategoryById(
            @PathVariable("id") Integer id,
            @RequestBody PmsProductCategoryDTO productCategoryDTO) {

        boolean res = productCategoryService.updateProductCategoryById(productCategoryDTO);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productCategory/list/withChildren',
     *     method:'get'
     */
    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    public CommonResult<List<PmsProductCategoryWithChildrenDTO>> getListWithChildren() {
        List<PmsProductCategoryWithChildrenDTO> productCategoryWithChildrenDTOS = productCategoryService.getListWithChildren();
        return CommonResult.success(productCategoryWithChildrenDTOS);
    }
}

