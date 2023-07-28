package com.alvin.mall.modules.sms.service;

import com.alvin.mall.modules.sms.dto.SmsHomeRecommendSubjectParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 首页推荐专题表 服务类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
public interface SmsHomeRecommendSubjectService extends IService<SmsHomeRecommendSubject> {

    Page<SmsHomeRecommendSubject> getList(SmsHomeRecommendSubjectParamDTO homeRecommendSubjectParamDTO);
}
