package com.alvin.mall.modules.pms.dto;

import com.alvin.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductCategoryWithChildrenDTO商品分类子分类数据传输对象", description = "用于查询商品分类子级数据POJO对象")
public class PmsProductCategoryWithChildrenDTO extends PmsProductCategory {

    private List<PmsProductCategory> children;

}
