package com.alvin.mall.dto;

import com.alvin.mall.modules.pms.model.PmsProductAttributeValue;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商品详细的商品属性（spu）数据传输对象")
public class PmsProductAttributeValueDTO extends PmsProductAttributeValue {

    private String attrName;

}
