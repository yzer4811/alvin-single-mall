package com.alvin.mall.modules.oms.service.impl;

import com.alvin.mall.modules.oms.model.OmsOrderItem;
import com.alvin.mall.modules.oms.mapper.OmsOrderItemMapper;
import com.alvin.mall.modules.oms.service.OmsOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-13
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {

}
