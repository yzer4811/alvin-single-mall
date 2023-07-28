package com.alvin.mall.modules.pms.service;

import com.alvin.mall.dto.ProductDetailDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
public interface PmsProductService extends IService<PmsProduct> {

    ProductDetailDTO getProductDetail(Long id);
}
