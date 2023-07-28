package com.alvin.mall.modules.sms.service;

import com.alvin.mall.modules.sms.dto.SmsHomeAdvertiseParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    Page<SmsHomeAdvertise> getList(SmsHomeAdvertiseParamDTO homeAdvertiseParamDTO);

    boolean updateStatus(Long id, Integer status);
}
