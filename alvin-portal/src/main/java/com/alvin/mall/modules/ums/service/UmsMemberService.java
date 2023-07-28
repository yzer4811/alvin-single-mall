package com.alvin.mall.modules.ums.service;

import com.alvin.mall.dto.MemberParamDTO;
import com.alvin.mall.modules.ums.model.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
public interface UmsMemberService extends IService<UmsMember> {

    UmsMember register(MemberParamDTO umsAdminParam);

    UmsMember login(String username, String password);

    UmsMember getAdminByUsername(String username);

    UmsMember loadUserByUsername(String username);

    UmsMember getCurrentMember();

}
