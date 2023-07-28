package com.alvin.mall.modules.pms.controller;


import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.pms.model.PmsBrand;
import com.alvin.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService brandService;

    /**
     * 品牌列表
     * url:'/brand/list',
     *     method:'get',
     *     params:params
     * http://localhost:8099/brand/list?pageNum=1&pageSize=10
     */
    @ApiOperation("获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> getList(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        Page<PmsBrand> page = brandService.getList(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/brand/update/factoryStatus',
     *     method:'post',
     *     data:{
     *         ids: 1
     *         factoryStatus: 0
     *     }
     * http://localhost:8099/brand/update/factoryStatus
     */
    @ApiOperation("修改品牌制造商显示状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult updateFactoryStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("factoryStatus") Long factoryStatus) {

        boolean res = brandService.updateFactoryStatus(ids, factoryStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/brand/update/showStatus',
     *     method:'post',
     *     data:{
     *         ids: 1,2,3,4,5,6,21,49,50,51
     *         showStatus: 0
     *     }
     * http://localhost:8099/brand/update/showStatus
     */
    @ApiOperation("批量修改品牌显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    public CommonResult updateShowStatus(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("showStatus") Long showStatus) {

        boolean res = brandService.updateShowStatus(ids, showStatus);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/brand/create',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody PmsBrand brand) {

        boolean res = brandService.save(brand);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/brand/'+id,
     *     method:'get',
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> getBrand(@PathVariable("id") Long id) {
        PmsBrand brand = brandService.getById(id);
        return CommonResult.success(brand);
    }

    /**
     * url:'/brand/update/'+id,
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(
            @PathVariable("id") Long id,
            @RequestBody PmsBrand brand) {

        boolean res = brandService.updateById(brand);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/brand/delete/'+id,
     *     method:'get',
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult deleteBrand(@PathVariable("id") Long id) {

        boolean res = brandService.removeById(id);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

