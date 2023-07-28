package com.alvin.mall.modules.ums.service.impl;

import com.alvin.mall.modules.ums.mapper.UmsMemberLevelMapper;
import com.alvin.mall.modules.ums.model.UmsMemberLevel;
import com.alvin.mall.modules.ums.service.UmsMemberLevelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-07
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public List<UmsMemberLevel> getList(Integer defaultStatus) {

        QueryWrapper<UmsMemberLevel> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UmsMemberLevel::getDefaultStatus, defaultStatus);
        return this.list(queryWrapper);
    }
}
