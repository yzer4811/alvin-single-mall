package com.alvin.mall.modules.sms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SmsHomeRecommendSubjectParamDTO专题推荐查询数据传输对象", description = "用于专题推荐的参数接收")
public class SmsHomeRecommendSubjectParamDTO {
    /**
     * pageNum: 1
     * pageSize: 5
     * subjectName: 123
     * recommendStatus: 0
     */
    private Integer pageNum;

    private Integer pageSize;

    private String subjectName;

    private Integer recommendStatus;

}
