package com.alvin.mall.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alvin.mall.modules.sms.dto.SmsHomeRecommendSubjectParamDTO;
import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import com.alvin.mall.modules.sms.model.SmsHomeRecommendSubject;
import com.alvin.mall.modules.sms.mapper.SmsHomeRecommendSubjectMapper;
import com.alvin.mall.modules.sms.service.SmsHomeRecommendSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页推荐专题表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-10
 */
@Service
public class SmsHomeRecommendSubjectServiceImpl extends ServiceImpl<SmsHomeRecommendSubjectMapper, SmsHomeRecommendSubject> implements SmsHomeRecommendSubjectService {

    @Override
    public Page<SmsHomeRecommendSubject> getList(SmsHomeRecommendSubjectParamDTO homeRecommendSubjectParamDTO) {
        Page<SmsHomeRecommendSubject> page = new Page<>(homeRecommendSubjectParamDTO.getPageNum(), homeRecommendSubjectParamDTO.getPageSize());

        QueryWrapper<SmsHomeRecommendSubject> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeRecommendSubject> lambda = queryWrapper.lambda();

//        private String subjectName;
//        private Integer recommendStatus;
        String subjectName = homeRecommendSubjectParamDTO.getSubjectName();
        Integer recommendStatus = homeRecommendSubjectParamDTO.getRecommendStatus();

        if (StrUtil.isNotBlank(subjectName)) {
            lambda.like(SmsHomeRecommendSubject::getSubjectName, subjectName.trim());
        }
        if (recommendStatus != null) {
            lambda.eq(SmsHomeRecommendSubject::getRecommendStatus, recommendStatus);
        }
        return this.page(page, queryWrapper);
    }
}
