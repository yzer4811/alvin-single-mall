package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.modules.pms.dto.PmsProductCategoryAttributeRelationDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.alvin.mall.modules.pms.mapper.PmsProductCategoryAttributeRelationMapper;
import com.alvin.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsProductCategoryAttributeRelationServiceImpl extends ServiceImpl<PmsProductCategoryAttributeRelationMapper, PmsProductCategoryAttributeRelation> implements PmsProductCategoryAttributeRelationService {

    @Autowired
    PmsProductCategoryAttributeRelationMapper relationMapper;

    @Override
    public List<PmsProductCategoryAttributeRelationDTO> getProductAttrInfo(Long id) {
        return relationMapper.getProductAttrInfo(id);
    }
}
