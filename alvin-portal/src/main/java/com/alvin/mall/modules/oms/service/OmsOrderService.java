package com.alvin.mall.modules.oms.service;

import com.alvin.mall.dto.ConfirmOrderDTO;
import com.alvin.mall.dto.OrderDetailDTO;
import com.alvin.mall.dto.OrderListDTO;
import com.alvin.mall.dto.OrderParamDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-13
 */
public interface OmsOrderService extends IService<OmsOrder> {

    ConfirmOrderDTO generateConfirmOrder(List<Long> ids);

    OmsOrder generateOrder(OrderParamDTO paramDTO);

    OrderDetailDTO getOrderDetail(Long id);

    IPage<OrderListDTO> getMyOrders(Integer pageSize, Integer pageNum);
}
