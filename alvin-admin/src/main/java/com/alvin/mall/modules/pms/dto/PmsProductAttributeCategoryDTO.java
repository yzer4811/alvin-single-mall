package com.alvin.mall.modules.pms.dto;

import com.alvin.mall.modules.pms.model.PmsProductAttribute;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PmsProductAttributeCategoryDTO筛选属性数据传输对象", description="用于商品分类--筛选属性下拉级联菜单")
public class PmsProductAttributeCategoryDTO {

    private Long id;

    private String name;

    private List<PmsProductAttribute> productAttributeList;
}
