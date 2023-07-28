package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.modules.pms.dto.PmsProductAttributeCategoryDTO;
import com.alvin.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.alvin.mall.modules.pms.model.PmsProductAttributeCategory;
import com.alvin.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.alvin.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public Page<PmsProductAttributeCategory> list(Integer pageNum, Integer pageSize) {

        Page<PmsProductAttributeCategory> page = new Page<>(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public List<PmsProductAttributeCategoryDTO> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }
}
