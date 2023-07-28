package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.dto.ProductDetailDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.alvin.mall.modules.pms.mapper.PmsProductMapper;
import com.alvin.mall.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    PmsProductMapper productMapper;

    @Override
    public ProductDetailDTO getProductDetail(Long id) {
        return productMapper.getProductDetail(id);
    }
}
