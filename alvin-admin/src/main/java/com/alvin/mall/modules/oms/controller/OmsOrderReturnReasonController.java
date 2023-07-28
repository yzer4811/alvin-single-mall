package com.alvin.mall.modules.oms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.alvin.mall.modules.oms.model.OmsOrderReturnReason;
import com.alvin.mall.modules.oms.service.OmsOrderReturnReasonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 退货原因表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/returnReason")
public class OmsOrderReturnReasonController {

    @Autowired
    OmsOrderReturnReasonService orderReturnReasonService;

    /**
     * url:'/returnReason/list',
     *     method:'get',
     *     params:params
     */
    @RequestMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrderReturnReason>> getList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<OmsOrderReturnReason> page = new Page<>(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderReturnReasonService.page(page)));
    }

    /**
     * 添加理由
     * url:'/returnReason/create',
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/create")
    public CommonResult createReturnReason(@RequestBody OmsOrderReturnReason orderReturnReason) {

        orderReturnReason.setCreateTime(new Date());
        boolean res = orderReturnReasonService.save(orderReturnReason);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 获取信息
     * url:'/returnReason/'+id,
     *     method:'get'
     */
    @RequestMapping(value = "/{id}")
    public CommonResult<OmsOrderReturnReason> getReasonDetail(@PathVariable("id") Long id) {

        OmsOrderReturnReason orderReturnReason = orderReturnReasonService.getById(id);
        if (BeanUtil.isEmpty(orderReturnReason)) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(orderReturnReason);
        }
    }

    /**
     * 更新理由
     * url:'/returnReason/update/'+id,
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/update/{id}")
    public CommonResult updateReturnReason(
            @PathVariable("id") Long id,
            @RequestBody OmsOrderReturnReason orderReturnReason) {

        boolean res = orderReturnReasonService.updateById(orderReturnReason);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 删除理由
     * url:'/returnReason/delete',
     *     method:'post',
     *     params:params
     */
    @RequestMapping(value = "/delete")
    public CommonResult deleteReturnReason(@RequestParam("ids") List<Long> ids) {

        QueryWrapper<OmsOrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(OmsOrderReturnReason::getId, ids);

        boolean res = orderReturnReasonService.remove(queryWrapper);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 更新是否可用
     * url:'/returnReason/update/status',
     *     method:'post',
     *     params:params
     */
    @RequestMapping(value = "/update/status")
    public CommonResult updateStatus(
            @RequestParam("ids") Long ids,
            @RequestParam("status") Integer status) {

        UpdateWrapper<OmsOrderReturnReason> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsOrderReturnReason::getStatus, status)
                .eq(OmsOrderReturnReason::getId, ids);

        boolean res = orderReturnReasonService.update(updateWrapper);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

