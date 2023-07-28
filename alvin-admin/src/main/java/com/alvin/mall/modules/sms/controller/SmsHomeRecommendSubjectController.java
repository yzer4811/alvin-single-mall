package com.alvin.mall.modules.sms.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.common.api.CommonPage;
import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.sms.dto.SmsHomeAdvertiseParamDTO;
import com.alvin.mall.modules.sms.dto.SmsHomeRecommendSubjectParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.alvin.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.alvin.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 首页推荐专题表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@RestController
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {

    @Autowired
    SmsHomeRecommendSubjectService homeRecommendSubjectService;

    /**
     * 获取专题推荐
     * url:'/home/recommendSubject/list',
     *     method:'get',
     *     params:params
     */
    @GetMapping("/list")
    @ApiOperation("获取专题推荐")
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> getList(SmsHomeRecommendSubjectParamDTO homeRecommendSubjectParamDTO) {

        Page<SmsHomeRecommendSubject> page = homeRecommendSubjectService.getList(homeRecommendSubjectParamDTO);
        if (BeanUtil.isEmpty(page)) {
            return CommonResult.failed();
        } else {
            return CommonResult.success(CommonPage.restPage(page));
        }
    }


}

