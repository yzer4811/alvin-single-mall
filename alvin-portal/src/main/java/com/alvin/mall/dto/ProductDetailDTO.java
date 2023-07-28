package com.alvin.mall.dto;

import com.alvin.mall.modules.pms.model.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商品详细信息数据传输对象")
public class ProductDetailDTO extends PmsProduct {

    @ApiModelProperty("商品属性相关(spu)")
    private List<PmsProductAttributeValueDTO> productAttributeValueList;

    @ApiModelProperty("商品sku库存信息")
    private List<PmsSkuStock> skuStockList;

}
