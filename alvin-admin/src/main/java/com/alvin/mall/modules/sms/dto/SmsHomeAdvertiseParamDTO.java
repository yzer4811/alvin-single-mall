package com.alvin.mall.modules.sms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SmsHomeAdvertiseParamDTO广告列表查询数据传输对象", description = "用于广告查询的参数接收")
public class SmsHomeAdvertiseParamDTO {

    /**
     * pageNum: 1
     * pageSize: 5
     * name: 123123123123
     * type: 0
     * endTime: 2023-06-25
     */

    private Integer pageNum;

    private Integer pageSize;

    private String name;

    private Integer type;

    private String endTime;

}
