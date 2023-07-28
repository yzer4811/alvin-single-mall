package com.alvin.mall.modules.oms.service;

import com.alvin.mall.modules.oms.dto.OmsOrderReturnApplyParamDTO;
import com.alvin.mall.modules.oms.model.OmsOrderReturnApply;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {

    Page<OmsOrderReturnApply> getList(OmsOrderReturnApplyParamDTO orderReturnApplyParamDTO);
}
