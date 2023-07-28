package com.alvin.mall.modules.pms.service;

import com.alvin.mall.modules.pms.dto.PmsProductCategoryDTO;
import com.alvin.mall.modules.pms.dto.PmsProductCategoryWithChildrenDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    /**
     * 获取商品列表
     * @param parentId 上级id
     * @param pageNum 第几页
     * @param pageSize 每页大小
     * @return 返回一个page对象
     */
    Page<PmsProductCategory> list(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * 修改导航栏显示状态
     * @param ids       多个需要修改的id
     * @param navStatus 修改后的值
     * @return 布尔值
     */
    boolean updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 修改显示状态
     * @param ids 同上
     * @param showStatus 同上
     * @return 布尔值
     */
    boolean updateShowStatus(List<Long> ids, Integer showStatus);

    boolean add(PmsProductCategoryDTO productCategory);

    boolean updateProductCategoryById(PmsProductCategoryDTO productCategoryDTO);

    List<PmsProductCategoryWithChildrenDTO> getListWithChildren();
}
