package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.modules.pms.model.PmsProductAttribute;
import com.alvin.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.alvin.mall.modules.pms.model.PmsProductAttributeCategory;
import com.alvin.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.alvin.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Wrapper;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    PmsProductAttributeCategoryService productAttributeCategoryService;

    @Override
    public Page<PmsProductAttribute> list(Integer cid, Integer pageNum, Integer pageSize, Integer type) {
        Page<PmsProductAttribute> page = new Page<>(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                .eq(PmsProductAttribute::getType, type)
                .orderByDesc(PmsProductAttribute::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    @Transactional
    public boolean save(PmsProductAttribute entity) {

        boolean res = super.save(entity);

        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        if (entity.getType() == 0) {
            updateWrapper.lambda()
                    .setSql("attribute_count = attribute_count + 1"); // 关联计数增加
        } else if (entity.getType() == 1) {
            updateWrapper.lambda()
                    .setSql("param_count = param_count + 1"); // 关联计数增加
        }
        updateWrapper.lambda()
                .eq(PmsProductAttributeCategory::getId, entity.getProductAttributeCategoryId());

        boolean res2 = productAttributeCategoryService.update(updateWrapper);
        return res && res2; // 通过两个结果值得出最终成功与否
    }

    @Transactional
    @Override
    public boolean delete(List<Long> ids) {

         // 注意，要先查元素，再删元素！！！！！！！！
        PmsProductAttribute productAttribute = null;
        if (ids.iterator().hasNext()) {
            productAttribute = this.getById(ids.iterator().next());
        }

        if (productAttribute == null) {
            return false;
        }

        int count = getBaseMapper().deleteBatchIds(ids);

        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        if (productAttribute.getType() == 0) {
            updateWrapper.lambda()
                    .setSql("attribute_count = attribute_count - " + count); // 关联计数增加
        } else if (productAttribute.getType() == 1) {
            updateWrapper.lambda()
                    .setSql("param_count = param_count - " + count); // 关联计数增加
        }
        updateWrapper.lambda().
                eq(PmsProductAttributeCategory::getId, productAttribute.getProductAttributeCategoryId());

        boolean res = productAttributeCategoryService.update(updateWrapper);
        return count != 0 && res; // 通过两个结果值得出最终成功与否
    }
}
