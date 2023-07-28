package com.alvin.mall.modules.pms.controller;


import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.pms.dto.PmsProductConditionDTO;
import com.alvin.mall.modules.pms.dto.PmsProductParamDTO;
import com.alvin.mall.modules.pms.dto.PmsProductUpdateInfoDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.alvin.mall.modules.pms.service.PmsProductCategoryService;
import com.alvin.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@RestController
@Api(tags = "PmsProductController", description = "后台商品管理")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    PmsProductService productService;

    /**
     * url:'/product/list',
     *     method:'get',
     *     params:params
     */
    @ApiOperation("获取商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductConditionDTO productConditionDTO) {
        Page<PmsProduct> page = productService.getList(productConditionDTO);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 批量更新上架状态
     * url:'/product/update/publishStatus',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量更新上架状态")
    @RequestMapping(value = "/update/publishStatus", method = RequestMethod.POST)
    public CommonResult updatePublishStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("publishStatus") Long publishStatus) {

        boolean res = productService.updateByPublishStatus(ids, publishStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 批量更新新品状态
     * url:'/product/update/newStatus',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量更新新品状态")
    @RequestMapping(value = "/update/newStatus", method = RequestMethod.POST)
    public CommonResult updateNewStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("newStatus") Long newStatus) {

        boolean res = productService.updateByNewStatus(ids, newStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 批量更新推荐状态
     * url:'/product/update/recommendStatus',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("批量更新推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    public CommonResult updateRecommendStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("newStatus") Long recommendStatus) {

        boolean res = productService.updateByRecommendStatus(ids, recommendStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 逻辑删除商品
     * url:'/product/update/deleteStatus',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("逻辑删除商品")
    @RequestMapping(value = "/update/deleteStatus", method = RequestMethod.POST)
    public CommonResult logicDelete(@RequestParam("ids") List<Long> ids) {
        boolean res = productService.removeByIds(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 添加商品
     * url:'/product/create',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("添加商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createProduct(@RequestBody @Valid PmsProductParamDTO productParamDTO) {

        boolean res = productService.saveAll(productParamDTO);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 获取商品更新信息
     * url: "/product/updateInfo/" + id,
     *     method: "get",
     */
    @RequestMapping(value = "/updateInfo/{id}", method = RequestMethod.GET)
    public CommonResult<PmsProductUpdateInfoDTO> updateInfo(@PathVariable("id") Long id) {
        PmsProductUpdateInfoDTO productUpdateInfoDTO = productService.getProduct(id);
        return CommonResult.success(productUpdateInfoDTO);
    }

    /**
     * 更新商品信息
     * url: "/product/update/" + id,
     *     method: "post",
     *     data: data,
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateById(@PathVariable("id") Long id,
                                   @RequestBody @Valid PmsProductParamDTO productParamDTO) {

        boolean res = productService.updateAll(id, productParamDTO);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

