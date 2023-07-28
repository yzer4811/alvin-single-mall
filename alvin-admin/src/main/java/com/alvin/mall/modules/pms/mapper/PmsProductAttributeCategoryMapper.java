package com.alvin.mall.modules.pms.mapper;

import com.alvin.mall.modules.pms.dto.PmsProductAttributeCategoryDTO;
import com.alvin.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductAttributeCategoryMapper extends BaseMapper<PmsProductAttributeCategory> {

    List<PmsProductAttributeCategoryDTO> getListWithAttr();
}
