package com.alvin.mall.modules.pms.service;

import com.alvin.mall.modules.pms.dto.PmsProductConditionDTO;
import com.alvin.mall.modules.pms.dto.PmsProductParamDTO;
import com.alvin.mall.modules.pms.dto.PmsProductUpdateInfoDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductService extends IService<PmsProduct> {

    Page<PmsProduct> getList(PmsProductConditionDTO productConditionDTO);

    boolean updateByPublishStatus(List<Long> ids, Long publishStatus);

    boolean updateByNewStatus(List<Long> ids, Long publishStatus);

    boolean updateByRecommendStatus(List<Long> ids, Long recommendStatus);

    boolean saveAll(PmsProductParamDTO productParamDTO);

    PmsProductUpdateInfoDTO getProduct(Long id);

    boolean updateAll(Long id, PmsProductParamDTO productParamDTO);
}
