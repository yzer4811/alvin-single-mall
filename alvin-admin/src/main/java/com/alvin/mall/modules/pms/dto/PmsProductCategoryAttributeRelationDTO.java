package com.alvin.mall.modules.pms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductAttributeCategoryRelationDTO商品分类与商品属性关联数据传输对象", description="用于接收商品分类与商品属性关联的POJO对象")
public class PmsProductCategoryAttributeRelationDTO {

    private Long attributeId;

    private Long attributeCategoryId;

}
