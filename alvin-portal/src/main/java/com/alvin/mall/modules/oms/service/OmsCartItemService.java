package com.alvin.mall.modules.oms.service;

import com.alvin.mall.dto.AddCarDTO;
import com.alvin.mall.dto.CartItemStockDTO;
import com.alvin.mall.modules.oms.model.OmsCartItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
public interface OmsCartItemService extends IService<OmsCartItem> {

    Boolean add(AddCarDTO addCarDTO);

    Integer getProductsSum();

    List<OmsCartItem> getCar();

    Boolean updateQuantity(Long id, Integer quantity);

    Boolean deleteCar(Long id);

    List<CartItemStockDTO> getCarWithStock();
}
