package com.alvin.mall.modules.pms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryDTO;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryWithChildrenDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.alvin.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.alvin.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.alvin.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.alvin.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    PmsProductCategoryAttributeRelationService relationService;

    @Autowired
    PmsProductCategoryMapper productCategoryMapper;

    @Override
    public Page<PmsProductCategory> list(Long parentId, Integer pageNum, Integer pageSize) {

        Page<PmsProductCategory> page = new Page<>(pageNum, pageSize);
//        条件构造器
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(PmsProductCategory::getParentId, parentId)
                .orderByDesc(PmsProductCategory::getSort);

        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                .set(PmsProductCategory::getNavStatus, navStatus)
                .in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {

        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                .set(PmsProductCategory::getShowStatus, showStatus)
                .in(PmsProductCategory::getId, ids);
        return this.update(pmsProductCategoryUpdateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(PmsProductCategoryDTO productCategory) {
        // 第一步：保存 商品分类记录
        boolean res1 = saveProductCategory(productCategory, 0);
        // 第二步：保存 商品分类 和 商品属性 之间 多对多映射 的中间表记录
        boolean res2 = saveProductCateAttrRela(productCategory, 0);
        return res1 && res2;
    }

    @Override
    public boolean updateProductCategoryById(PmsProductCategoryDTO productCategory) {
        // 第一步：保存 商品分类记录
        boolean res1 = saveProductCategory(productCategory, 1);
        // 第二部：保存 商品分类 和 商品属性 之间 多对多映射 的中间表记录
        boolean res2 = saveProductCateAttrRela(productCategory, 1);
        return res1 && res2;
    }

    @Override
    public List<PmsProductCategoryWithChildrenDTO> getListWithChildren() {
        return productCategoryMapper.getListWithChildren();
    }

    /**
     * 保存或更新数据
     * @param source 数据传输对象
     * @param type 状态值：0保存；1更新
     * @return 布尔值
     */
    private boolean saveProductCategory(PmsProductCategoryDTO source, int type) {

        // 从 DTO 中提取 PmsProductCategory pojo对象
        PmsProductCategory target = new PmsProductCategory();
        BeanUtils.copyProperties(source, target); // 使用 spring 自带方法复制属性值
        System.out.println(target);

        if (type == 0) {
            // 设置商品数量默认值
            target.setProductCount(0);
        }

        // 设置商品级别
        if (target.getParentId() == 0) {
            target.setLevel(0);
        } else {
            PmsProductCategory parentProductCate = this.getById(target.getParentId());
            Integer parentProductCateLevel = parentProductCate.getLevel();
            target.setLevel(parentProductCateLevel + 1);
        }

        if (type == 0) {
            boolean res = this.save(target);
            // 添加新数据前还没有主键id值，需要在查询后传递出去，否则给关联表添加数据时会null映射
            source.setId(target.getId());
            return res;
        } else if (type == 1) {
            return this.updateById(target);
        } else {
            return false;
        }

    }

    /**
     * 保存或更新商品分类与商品属性关联表
     * @param productCategoryDTO 数据传输对象
     * @param type 状态值：0保存；1更新
     * @return 布尔值
     */
    private boolean saveProductCateAttrRela(PmsProductCategoryDTO productCategoryDTO, int type) {
        List<Long> ids = productCategoryDTO.getIds();
        if (ids.isEmpty()) return true; // 如果 ids 为空，说明没有添加任何筛选属性，直接返回true
        List<PmsProductCategoryAttributeRelation> relations = new ArrayList<>();
        for (Long id : ids) {
            PmsProductCategoryAttributeRelation relation = new PmsProductCategoryAttributeRelation();
            System.out.println("productCategoryDTO.getId():" + productCategoryDTO.getId());
            relation.setProductCategoryId(productCategoryDTO.getId());
            relation.setProductAttributeId(id);
            relations.add(relation);
        }
        if (type == 1) {
            QueryWrapper<PmsProductCategoryAttributeRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, productCategoryDTO.getId());
            relationService.remove(queryWrapper);
        }
        return relationService.saveBatch(relations);
    }
}
