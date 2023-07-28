package com.alvin.mall.modules.pms.service;

import com.alvin.mall.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {

    Page<PmsProductAttribute> list(Integer cid, Integer pageNum, Integer pageSize, Integer type);

    boolean delete(List<Long> ids);
}
