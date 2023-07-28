package com.alvin.mall.modules.oms.mapper;

import com.alvin.mall.modules.oms.dto.OmsOrderInfoDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    OmsOrderInfoDTO getOrderInfo(Long id);
}
