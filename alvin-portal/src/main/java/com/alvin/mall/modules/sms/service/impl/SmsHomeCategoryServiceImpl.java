package com.alvin.mall.modules.sms.service.impl;

import com.alvin.mall.dto.HomeGoodsSaleDTO;
import com.alvin.mall.modules.sms.model.SmsHomeCategory;
import com.alvin.mall.modules.sms.mapper.SmsHomeCategoryMapper;
import com.alvin.mall.modules.sms.service.SmsHomeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-11
 */
@Service
public class SmsHomeCategoryServiceImpl extends ServiceImpl<SmsHomeCategoryMapper, SmsHomeCategory> implements SmsHomeCategoryService {

    @Autowired
    SmsHomeCategoryMapper categoryMapper;


    @Override
    public List<HomeGoodsSaleDTO> getGoodsSale() {
        return categoryMapper.getHomeCategoryWithProducts();
    }
}
