package com.alvin.mall.modules.oms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.oms.model.OmsOrderSetting;
import com.alvin.mall.modules.oms.service.OmsOrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 订单设置表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/orderSetting")
public class OmsOrderSettingController {

    @Autowired
    OmsOrderSettingService orderSettingService;

    /**
     * url: "/orderSetting/" + id,
     *     method: "get",
     * http://localhost:8099/orderSetting/1
     */
    @RequestMapping(value = "/{id}")
    public CommonResult<OmsOrderSetting> getOrderSetting(@PathVariable("id") Long id) {

        OmsOrderSetting orderSetting = orderSettingService.getById(id);
        if (BeanUtil.isEmpty(orderSetting)) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(orderSetting);
        }
    }

    /**
     * url: "/orderSetting/update/" + id,
     *     method: "post",
     *     data: data,
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateOrderSetting(
            @PathVariable("id") Long id,
            @RequestBody OmsOrderSetting orderSetting) {

        boolean res = orderSettingService.updateById(orderSetting);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

