package com.alvin.mall.modules.sms.service;

import com.alvin.mall.dto.HomeGoodsSaleDTO;
import com.alvin.mall.modules.sms.model.SmsHomeCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-11
 */
public interface SmsHomeCategoryService extends IService<SmsHomeCategory> {

    List<HomeGoodsSaleDTO> getGoodsSale();
}
