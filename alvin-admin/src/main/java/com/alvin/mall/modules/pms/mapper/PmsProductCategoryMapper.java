package com.alvin.mall.modules.pms.mapper;

import com.alvin.mall.modules.pms.dto.PmsProductCategoryWithChildrenDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {

    List<PmsProductCategoryWithChildrenDTO> getListWithChildren();
}
