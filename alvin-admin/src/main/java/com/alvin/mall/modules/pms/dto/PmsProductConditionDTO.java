package com.alvin.mall.modules.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductConditionDTO商品列表条件", description = "用于商品列表条件数据传输的POJO对象")
public class PmsProductConditionDTO {
    /**
     * 前端返回数据格式
     * keyword: a
     * pageNum: 1
     * pageSize: 5
     * publishStatus: 1
     * verifyStatus: 1
     * productSn: a
     * productCategoryId: 31
     * brandId: 2
     */

    private String keyword;

    private Integer pageNum;

    private Integer pageSize;

    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Long publishStatus;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Long verifyStatus;

    @ApiModelProperty(value = "货号")
    private String productSn;

    private Long productCategoryId;

    private Long brandId;

}
