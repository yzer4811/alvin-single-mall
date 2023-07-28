package com.alvin.mall.modules.pms.mapper;

import com.alvin.mall.modules.pms.dto.PmsProductUpdateInfoDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    PmsProductUpdateInfoDTO getProduct(Long id);
}
