package com.alvin.mall.modules.oms.mapper;

import com.alvin.mall.dto.CartItemStockDTO;
import com.alvin.mall.modules.oms.model.OmsCartItem;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
public interface OmsCartItemMapper extends BaseMapper<OmsCartItem> {

    List<CartItemStockDTO> getCartItemStock(Long id);

    List<CartItemStockDTO> getCartItemStockByIds(@Param(Constants.WRAPPER) Wrapper ew);
}
