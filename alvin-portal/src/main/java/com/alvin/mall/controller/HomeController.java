package com.alvin.mall.controller;


import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.dto.HomeGoodsSaleDTO;
import com.alvin.mall.dto.HomeMenusBannersDTO;
import com.alvin.mall.modules.pms.service.PmsProductCategoryService;
import com.alvin.mall.modules.sms.service.SmsHomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 相册表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    PmsProductCategoryService productCategoryService;

    @Autowired
    SmsHomeCategoryService smsHomeCategoryService;

    /**
     * 获取首页类型导航栏和数据
     * get("/home/menus")
     */
    @GetMapping(value = "/menus_banners")
    public CommonResult<HomeMenusBannersDTO> getMenusAndBanners() {

        HomeMenusBannersDTO homeMenusBannersDTO = productCategoryService.getMenusAndBanners();
        return CommonResult.success(homeMenusBannersDTO);
    }

    @GetMapping(value = "/goods_sale")
    public CommonResult<List<HomeGoodsSaleDTO>> getGoodsSale() {
        List<HomeGoodsSaleDTO> list = smsHomeCategoryService.getGoodsSale();
        return CommonResult.success(list);
    }
}

