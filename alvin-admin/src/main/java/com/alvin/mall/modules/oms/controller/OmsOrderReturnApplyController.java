package com.alvin.mall.modules.oms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.oms.dto.OmsOrderReturnApplyParamDTO;
import com.alvin.mall.modules.oms.model.OmsOrderReturnApply;
import com.alvin.mall.modules.oms.model.OmsOrderReturnReason;
import com.alvin.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单退货申请 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {

    @Autowired
    OmsOrderReturnApplyService orderReturnApplyService;

    /**
     * 退货申请列表
     * url:'/returnApply/list',
     *     method:'get',
     *     params:params
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrderReturnApply>> getList(OmsOrderReturnApplyParamDTO orderReturnApplyParamDTO) {
        Page<OmsOrderReturnApply> page = orderReturnApplyService.getList(orderReturnApplyParamDTO);
        if (BeanUtil.isEmpty(page)) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(CommonPage.restPage(page));
        }
    }

    /**
     * 批量删除
     * http://localhost:8099/returnApply/delete?ids=15
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult deleteByIds(@RequestParam("ids") List<Long> ids) {

        boolean res = orderReturnApplyService.removeByIds(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 获取退货订单信息
     * url:'/returnApply/'+id,
     *     method:'get'
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<OmsOrderReturnApply> getById(@PathVariable("id") Long id) {

        OmsOrderReturnApply orderReturnApply = orderReturnApplyService.getById(id);
        if (BeanUtil.isEmpty(orderReturnApply)) {
            return CommonResult.failed();
        }
        return CommonResult.success(orderReturnApply);
    }

    /**
     * 修改退货状态
     * 申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝
     * url:'/returnApply/update/status/'+id,
     *     method:'post',
     *     data:data
     */
    @RequestMapping(value = "/update/status/{id}")
    public CommonResult updateReturnReason(
            @PathVariable("id") Long id,
            @RequestBody OmsOrderReturnApply orderReturnApply) {

        orderReturnApply.setId(id);
        orderReturnApply.setHandleTime(new Date());
        boolean res = orderReturnApplyService.updateById(orderReturnApply);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}

