package com.alvin.mall.modules.pms.dto;

import com.alvin.mall.modules.pms.model.PmsProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductCategoryDTO商品分类数据传输对象", description="用于接收商品分类传输数据的POJO对象")
public class PmsProductCategoryDTO extends PmsProductCategory {
    @JsonProperty("productAttributeIdList")
    private List<Long> ids;
}
