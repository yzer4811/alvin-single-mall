package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.dto.HomeMenusBannersDTO;
import com.alvin.mall.dto.HomeMenusDTO;
import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.alvin.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.alvin.mall.modules.pms.service.PmsProductCategoryService;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.alvin.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {

    @Autowired
    PmsProductCategoryMapper categoryMapper;

    @Autowired
    SmsHomeAdvertiseService advertiseService;

    @Override
    public List<HomeMenusDTO> getMenus() {
        return categoryMapper.getProductWithCategory();
    }

    @Override
    public HomeMenusBannersDTO getMenusAndBanners() {
        HomeMenusBannersDTO homeMenusBannersDTO = new HomeMenusBannersDTO();
        homeMenusBannersDTO.setHomeMenusDTOList(categoryMapper.getProductWithCategory());

        UpdateWrapper<SmsHomeAdvertise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(SmsHomeAdvertise::getType, 0) // 查询pc广告
                .eq(SmsHomeAdvertise::getStatus, 1); // 查询上线广告

        homeMenusBannersDTO.setHomeAdvertiseList(advertiseService.list(updateWrapper));
        return homeMenusBannersDTO;
    }
}
