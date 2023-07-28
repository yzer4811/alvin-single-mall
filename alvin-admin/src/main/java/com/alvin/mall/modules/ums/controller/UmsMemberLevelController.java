package com.alvin.mall.modules.ums.controller;


import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.ums.model.UmsMemberLevel;
import com.alvin.mall.modules.ums.service.UmsMemberLevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 会员等级表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-07
 */
@RestController
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService memberLevelService;

    /**
     * http://localhost:8099/memberLevel/list?defaultStatus=0
     */
    @ApiOperation("获取成员等级列表")
    @RequestMapping(value = "/list")
    public CommonResult<List<UmsMemberLevel>> getList(@RequestParam(value = "defaultStatus", defaultValue = "o") Integer defaultStatus) {

        List<UmsMemberLevel> list = memberLevelService.getList(defaultStatus);
        return CommonResult.success(list);
    }
}

