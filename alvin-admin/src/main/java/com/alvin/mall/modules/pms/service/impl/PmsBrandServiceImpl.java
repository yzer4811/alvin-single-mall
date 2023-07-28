package com.alvin.mall.modules.pms.service.impl;

import com.alvin.mall.modules.pms.model.PmsBrand;
import com.alvin.mall.modules.pms.mapper.PmsBrandMapper;
import com.alvin.mall.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Override
    public Page<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize) {

        Page<PmsBrand> brandPage = new Page<>(pageNum, pageSize);

        if ("".equals(keyword)) {
            return this.page(brandPage);
        } else {
            QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda()
                    .like(PmsBrand::getName, keyword); // 使用模糊查询，不区分大小写
            return this.page(brandPage, queryWrapper);
        }
    }

    @Override
    public boolean updateFactoryStatus(List<Long> ids, Long factoryStatus) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsBrand::getFactoryStatus, factoryStatus)
                .in(PmsBrand::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateShowStatus(List<Long> ids, Long showStatus) {
        UpdateWrapper<PmsBrand> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsBrand::getShowStatus, showStatus)
                .in(PmsBrand::getId, ids);
        return this.update(updateWrapper);
    }

}
