package com.alvin.mall.modules.pms.service;

import com.alvin.mall.dto.HomeMenusBannersDTO;
import com.alvin.mall.dto.HomeMenusDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {

    List<HomeMenusDTO> getMenus();

    HomeMenusBannersDTO getMenusAndBanners();
}
