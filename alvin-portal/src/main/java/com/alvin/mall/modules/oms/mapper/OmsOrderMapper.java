package com.alvin.mall.modules.oms.mapper;

import com.alvin.mall.dto.OrderDetailDTO;
import com.alvin.mall.dto.OrderListDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-06-13
 */
public interface OmsOrderMapper extends BaseMapper<OmsOrder> {

    OrderDetailDTO getOrderDetail(Long id);

    IPage<OrderListDTO> getMyOrders(Page<?> page, @Param("memberId") Long memberId);
}
