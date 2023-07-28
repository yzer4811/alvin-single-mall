package com.alvin.mall.modules.oms.service;

import com.alvin.mall.modules.oms.dto.OmsOrderFindParamDTO;
import com.alvin.mall.modules.oms.dto.OmsOrderInfoDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
public interface OmsOrderService extends IService<OmsOrder> {

    Page<OmsOrder> getList(OmsOrderFindParamDTO orderFindParamDTO);

    boolean deleteByIds(List<Long> ids);

    boolean closeOrder(List<Long> ids, String note);

    OmsOrderInfoDTO getOrderInfo(Long id);
}
