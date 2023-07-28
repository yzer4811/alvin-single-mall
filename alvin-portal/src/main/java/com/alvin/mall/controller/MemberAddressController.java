package com.alvin.mall.controller;


import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.modules.ums.mapper.UmsMemberMapper;
import com.alvin.mall.modules.ums.model.UmsMemberReceiveAddress;
import com.alvin.mall.modules.ums.service.UmsMemberReceiveAddressService;
import com.alvin.mall.modules.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员收货地址表 前端控制器
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
@RestController
@RequestMapping("/member/address")
public class MemberAddressController {

    @Autowired
    UmsMemberReceiveAddressService memberReceiveAddressService;
    @Autowired
    UmsMemberService memberService;

    /**
     * 获取用户地址
     * axios.get('/member/address/list')
     */
    @GetMapping("/list")
    public CommonResult<List<UmsMemberReceiveAddress>> getList() {

        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UmsMemberReceiveAddress::getMemberId, memberService.getCurrentMember().getId());
        List<UmsMemberReceiveAddress> list = memberReceiveAddressService.list(queryWrapper);
        if (list != null) {
            return CommonResult.success(list);
        }
        return CommonResult.failed();
    }

    /**
     * 提添加用户地址
     * /member/address/add
     */
    @PostMapping("/add")
    public CommonResult addAddress(@RequestBody UmsMemberReceiveAddress memberReceiveAddress) {

        memberReceiveAddress.setMemberId(memberService.getCurrentMember().getId());
        boolean res = memberReceiveAddressService.save(memberReceiveAddress);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 更新用户地址
     * /member/address/update/${checkedItem.id}
     */
    @PostMapping("/update/{id}")
    public CommonResult updateAddress(
            @PathVariable("id") Long id,
            @RequestBody UmsMemberReceiveAddress memberReceiveAddress) {

        memberReceiveAddress.setId(id);
        boolean res = memberReceiveAddressService.updateById(memberReceiveAddress);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 删除用户地址
     * /member/address/delete/${checkedItem.id}
     */
    @PostMapping("/delete/{id}")
    public CommonResult deleteAddress(@PathVariable("id") Long id) {

        boolean res = memberReceiveAddressService.removeById(id);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

}

