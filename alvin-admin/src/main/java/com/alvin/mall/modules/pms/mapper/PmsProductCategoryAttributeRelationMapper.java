package com.alvin.mall.modules.pms.mapper;

import com.alvin.mall.modules.pms.dto.PmsProductCategoryAttributeRelationDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductCategoryAttributeRelationMapper extends BaseMapper<PmsProductCategoryAttributeRelation> {

    List<PmsProductCategoryAttributeRelationDTO> getProductAttrInfo(Long id);
}
