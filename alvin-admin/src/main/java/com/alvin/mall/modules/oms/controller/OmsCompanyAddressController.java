package com.alvin.mall.modules.oms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.oms.model.OmsCompanyAddress;
import com.alvin.mall.modules.oms.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 公司收发货地址表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/companyAddress")
public class OmsCompanyAddressController {

    @Autowired
    OmsCompanyAddressService companyAddressService;

    /**
     * url:'/companyAddress/list',
     *     method:'get'
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<OmsCompanyAddress>> getList() {
        List<OmsCompanyAddress> list = companyAddressService.list();
        if (BeanUtil.isEmpty(list)) {
            return CommonResult.failed();
        }
        return CommonResult.success(list);
    }
}

