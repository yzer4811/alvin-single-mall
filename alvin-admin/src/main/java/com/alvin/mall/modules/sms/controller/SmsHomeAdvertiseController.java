package com.alvin.mall.modules.sms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.sms.dto.SmsHomeAdvertiseParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.alvin.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/home/advertise")
public class SmsHomeAdvertiseController {

    @Autowired
    SmsHomeAdvertiseService homeAdvertiseService;

    /**
     * url:'/home/advertise/list',
     *     method:'get',
     *     params:params
     */
    @GetMapping("/list")
    @ApiOperation("获取广告列表")
    public CommonResult<CommonPage<SmsHomeAdvertise>> getList(SmsHomeAdvertiseParamDTO homeAdvertiseParamDTO) {

        Page<SmsHomeAdvertise> page = homeAdvertiseService.getList(homeAdvertiseParamDTO);
        if (BeanUtil.isEmpty(page)) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(CommonPage.restPage(page));
        }

    }

    /**'
     * 添加广告
     * url:'/home/advertise/create',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/create")
    @ApiOperation("添加广告")
    public CommonResult addAdvertise(@RequestBody SmsHomeAdvertise homeAdvertise) {

        boolean res = homeAdvertiseService.save(homeAdvertise);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 查询广告
     * url:'/home/advertise/'+id,
     *     method:'get',
     */
    @GetMapping("/{id}")
    public CommonResult<SmsHomeAdvertise> getAdvertise(@PathVariable("id") Long id) {

        SmsHomeAdvertise homeAdvertise = homeAdvertiseService.getById(id);
        if (BeanUtil.isEmpty(homeAdvertise)) {
            return CommonResult.failed();
        }
        return CommonResult.success(homeAdvertise);
    }

    /**
     * 更改广告
     * url:'/home/advertise/update/'+id,
     *     method:'post',
     *     data:data
     */
    @PostMapping("/update/{id}")
    @ApiOperation("更改广告")
    public CommonResult updateAdvertise(@RequestBody SmsHomeAdvertise homeAdvertise) {

        boolean res = homeAdvertiseService.updateById(homeAdvertise);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 更新是否上线
     * url:'/home/advertise/update/status/'+id,
     *     method:'post',
     *     params:params
     */
    @PostMapping("/update/status/{id}")
    @ApiOperation("更新是否上线")
    public CommonResult updateStatus(@PathVariable("id") Long id,
                                     @RequestParam("status") Integer status) {

        boolean res = homeAdvertiseService.updateStatus(id, status);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 删除广告
     * url:'/home/advertise/delete',
     *     method:'post',
     *     data:data
     */
    @PostMapping("/delete")
    @ApiOperation("删除广告")
    public CommonResult updateStatus(@RequestParam("ids") List<Long> ids) {

        boolean res = homeAdvertiseService.removeByIds(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

