package com.alvin.mall.modules.oms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alvin.mall.modules.oms.dto.OmsOrderReturnApplyParamDTO;
import com.alvin.mall.modules.oms.model.OmsOrderReturnApply;
import com.alvin.mall.modules.oms.mapper.OmsOrderReturnApplyMapper;
import com.alvin.mall.modules.oms.service.OmsOrderReturnApplyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单退货申请 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@Service
public class OmsOrderReturnApplyServiceImpl extends ServiceImpl<OmsOrderReturnApplyMapper, OmsOrderReturnApply> implements OmsOrderReturnApplyService {

    @Override
    public Page<OmsOrderReturnApply> getList(OmsOrderReturnApplyParamDTO orderReturnApplyParamDTO) {

        /**
         * pageNum: 1
         * pageSize: 10
         * id: 123 Long
         * status: 0 Integer
         * createTime: 2023-06-19 Date
         * handleMan: 456 String
         * handleTime: 2023-06-26
         */

        Page<OmsOrderReturnApply> page = new Page<>(orderReturnApplyParamDTO.getPageNum(), orderReturnApplyParamDTO.getPageSize());

        QueryWrapper<OmsOrderReturnApply> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<OmsOrderReturnApply> lambda = queryWrapper.lambda();

        if (orderReturnApplyParamDTO.getId() != null) {
            lambda.like(OmsOrderReturnApply::getId, orderReturnApplyParamDTO.getId());
        }

        if (orderReturnApplyParamDTO.getStatus() != null) {
            lambda.eq(OmsOrderReturnApply::getStatus, orderReturnApplyParamDTO.getStatus());
        }

        if (StrUtil.isNotBlank(orderReturnApplyParamDTO.getCreateTime())) {
            lambda.like(OmsOrderReturnApply::getCreateTime, orderReturnApplyParamDTO.getCreateTime().trim());
        }

        if (StrUtil.isNotBlank(orderReturnApplyParamDTO.getHandleMan())) {
            lambda.like(OmsOrderReturnApply::getHandleMan, orderReturnApplyParamDTO.getHandleMan().trim());
        }

        if (StrUtil.isNotBlank(orderReturnApplyParamDTO.getHandleTime())) {
            lambda.like(OmsOrderReturnApply::getHandleTime, orderReturnApplyParamDTO.getHandleTime().trim());
        }

        return this.page(page, queryWrapper);
    }
}
