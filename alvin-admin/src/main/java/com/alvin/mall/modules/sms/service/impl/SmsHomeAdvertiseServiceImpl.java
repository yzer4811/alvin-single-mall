package com.alvin.mall.modules.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alvin.mall.modules.sms.dto.SmsHomeAdvertiseParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.alvin.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.alvin.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {

    @Override
    public Page<SmsHomeAdvertise> getList(SmsHomeAdvertiseParamDTO homeAdvertiseParamDTO) {

        Page<SmsHomeAdvertise> page = new Page<>(homeAdvertiseParamDTO.getPageNum(), homeAdvertiseParamDTO.getPageSize());

        QueryWrapper<SmsHomeAdvertise> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeAdvertise> lambda = queryWrapper.lambda();

        // private String name;
        // private Integer type;
        // private String endTime;
        String name = homeAdvertiseParamDTO.getName();
        Integer type = homeAdvertiseParamDTO.getType();
        String endTime = homeAdvertiseParamDTO.getEndTime();

        if (StrUtil.isNotBlank(name)) {
            lambda.like(SmsHomeAdvertise::getName, name.trim());
        }
        if (type!=null) {
            lambda.eq(SmsHomeAdvertise::getType, type);
        }
        if (StrUtil.isNotBlank(endTime)) {
            lambda.like(SmsHomeAdvertise::getEndTime, endTime.trim());
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        UpdateWrapper<SmsHomeAdvertise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SmsHomeAdvertise::getStatus, status)
                .eq(SmsHomeAdvertise::getId, id);
        return this.update(updateWrapper);
    }



}
