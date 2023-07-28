package com.alvin.mall.modules.ums.service;

import com.alvin.mall.modules.ums.model.UmsMemberLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-07
 */
public interface UmsMemberLevelService extends IService<UmsMemberLevel> {

    List<UmsMemberLevel> getList(Integer defaultStatus);
}
