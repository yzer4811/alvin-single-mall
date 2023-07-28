package com.alvin.mall.modules.pms.dto;

import com.alvin.mall.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductParamDTO商品参数传输对象", description = "用于商品添加、修改参数传输")
public class PmsProductParamDTO extends PmsProduct {

    /**
     * memberPriceList
     * productAttributeValueList
     * productFullReductionList
     * productLadderList
     * skuStockList
     */
    @ApiModelProperty("会员价格")
    private List<PmsMemberPrice> memberPriceList;

    @ApiModelProperty("商品属性相关")
    private List<PmsProductAttributeValue> productAttributeValueList;

    @ApiModelProperty("商品满减")
    private List<PmsProductFullReduction> productFullReductionList;

    @ApiModelProperty("商品阶梯价格")
    private List<PmsProductLadder> productLadderList;

    @ApiModelProperty("商品sku库存信息")
    @Size(min = 1, message= "sku至少需要一条")
    @Valid
    private List<PmsSkuStock> skuStockList;

}
