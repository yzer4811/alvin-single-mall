package com.alvin.mall.modules.sms.mapper;

import com.alvin.mall.dto.HomeGoodsSaleDTO;
import com.alvin.mall.modules.sms.model.SmsHomeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-06-11
 */
public interface SmsHomeCategoryMapper extends BaseMapper<SmsHomeCategory> {

    List<HomeGoodsSaleDTO> getHomeCategoryWithProducts();
}
