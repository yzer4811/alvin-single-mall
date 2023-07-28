package com.alvin.mall.modules.pms.service;

import com.alvin.mall.modules.pms.model.PmsBrand;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
public interface PmsBrandService extends IService<PmsBrand> {

    boolean updateFactoryStatus(List<Long> ids, Long factoryStatus);

    boolean updateShowStatus(List<Long> ids, Long showStatus);

    Page<PmsBrand> getList(String keyword, Integer pageNum, Integer pageSize);
}
