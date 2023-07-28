package com.alvin.mall.modules.pms.mapper;

import com.alvin.mall.dto.ProductDetailDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    ProductDetailDTO getProductDetail(Long id);
}
