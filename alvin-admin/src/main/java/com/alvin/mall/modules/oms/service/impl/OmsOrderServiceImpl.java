package com.alvin.mall.modules.oms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.alvin.mall.modules.oms.dto.OmsOrderFindParamDTO;
import com.alvin.mall.modules.oms.dto.OmsOrderInfoDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.alvin.mall.modules.oms.mapper.OmsOrderMapper;
import com.alvin.mall.modules.oms.service.OmsOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-09
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {

    @Autowired
    OmsOrderMapper orderMapper;

    @Override
    public Page<OmsOrder> getList(OmsOrderFindParamDTO orderFindParamDTO) {

        Page<OmsOrder> page = new Page<>(orderFindParamDTO.getPageNum(), orderFindParamDTO.getPageSize());

        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<OmsOrder> lambda = queryWrapper.lambda();

        String receiverKeyword = orderFindParamDTO.getReceiverKeyword();
        if (receiverKeyword != null) {
            receiverKeyword = receiverKeyword.trim();
            // 判断如果是纯数字串，就检索手机号
            if (ReUtil.isMatch("\\d+", receiverKeyword)) {
                lambda.like(OmsOrder::getReceiverPhone, receiverKeyword);
            } else { // 否则检索姓名
                lambda.like(OmsOrder::getReceiverName, receiverKeyword);
            }
        }

        if (orderFindParamDTO.getStatus() != null) {
            lambda.eq(OmsOrder::getStatus, orderFindParamDTO.getStatus());
        }

        if (orderFindParamDTO.getOrderType() != null) {
            lambda.eq(OmsOrder::getOrderType, orderFindParamDTO.getOrderType());
        }

        if (orderFindParamDTO.getSourceType() != null) {
            lambda.eq(OmsOrder::getSourceType, orderFindParamDTO.getSourceType());
        }

        if (orderFindParamDTO.getCreateTime() != null) {
            String createTime = orderFindParamDTO.getCreateTime().trim();
            lambda.like(OmsOrder::getCreateTime, createTime);
        }

        if (StrUtil.isNotBlank(orderFindParamDTO.getOrderSn())) {
            lambda.like(OmsOrder::getOrderSn, orderFindParamDTO.getOrderSn().trim());
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {

        QueryWrapper<OmsOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(OmsOrder::getId, ids);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean closeOrder(List<Long> ids, String note) {

        UpdateWrapper<OmsOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsOrder::getNote, note)
                // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
                .set(OmsOrder::getStatus, 4)
                .in(OmsOrder::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public OmsOrderInfoDTO getOrderInfo(Long id) {
        return orderMapper.getOrderInfo(id);
    }
}
