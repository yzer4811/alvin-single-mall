package com.alvin.mall.modules.oms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.oms.dto.OmsOrderFindParamDTO;
import com.alvin.mall.modules.oms.dto.OmsOrderInfoDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.alvin.mall.modules.oms.service.OmsOrderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.protocol.x.XServerCapabilities;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/order")
public class OmsOrderController {

    @Autowired
    OmsOrderService orderService;

    /**
     * 获取订单列表
     * url:'/order/list',
     *     method:'get',
     *     params:
     *          pageNum: 1
     *          pageSize: 10
     */
    @ApiOperation("获取订单")
    @RequestMapping(value = "/list")
    public CommonResult<CommonPage<OmsOrder>> getList(OmsOrderFindParamDTO orderFindParamDTO) {

        Page<OmsOrder> page = orderService.getList(orderFindParamDTO);
        if (page != null) {
            return CommonResult.success(CommonPage.restPage(page));
        }
        return CommonResult.failed();
    }

    /**
     * 删除订单（逻辑删除）
     * url:'/order/delete',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult deleteOrderByIds(@RequestParam("ids") List<Long> ids) {

        boolean res = orderService.deleteByIds(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 关闭订单
     * url:'/order/update/close',
     *     method:'post',
     *     params:
     *          ids: 21
     *          note: xxx
     */
    @ApiOperation("关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    public CommonResult closeOrder(
            @RequestParam("ids") List<Long> ids,
            @RequestParam("note") String note) {

        boolean res = orderService.closeOrder(ids, note);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 订单发货
     * url:'/order/update/delivery',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("订单发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public CommonResult updateDelivery(@RequestBody @Valid List<OmsOrder> orders) {

        boolean res = orderService.updateBatchById(orders);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 查看订单
     * url:'/order/'+id,
     *     method:'get'
     */
    @ApiOperation("查看订单")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<OmsOrderInfoDTO> getOrderInfo(@PathVariable("id") Long id) {

        OmsOrderInfoDTO orderInfoDTO = orderService.getOrderInfo(id);
        if (BeanUtil.isEmpty(orderInfoDTO)) {
            return CommonResult.failed();
        }
        return CommonResult.success(orderInfoDTO);
    }

    /**
     * 更新备注信息
     * url:'/order/update/note',
     *     method:'post',
     *     params:params
     */
    @ApiOperation("更新备注信息")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public CommonResult updateNote(OmsOrder order) {

        boolean res = orderService.updateById(order);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/order/update/receiverInfo',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("更新收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsOrder order) {

        boolean res = orderService.updateById(order);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 更新订单商品费用
     * url:'/order/update/moneyInfo',
     *     method:'post',
     *     data:data
     */
    @ApiOperation("更新订单商品费用")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    public CommonResult updateMoneyInfo(@RequestBody OmsOrder order) {

        boolean res = orderService.updateById(order);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

