package com.alvin.mall.modules.pms.service;

import com.alvin.mall.modules.pms.dto.PmsProductAttributeCategoryDTO;
import com.alvin.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {

    /**
     * 根据分页数据返回商品属性类别列表
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @return Page
     */
    Page<PmsProductAttributeCategory> list(Integer pageNum, Integer pageSize);

    List<PmsProductAttributeCategoryDTO> getListWithAttr();
}
