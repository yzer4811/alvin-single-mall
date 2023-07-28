package com.alvin.mall.modules.pms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductUpdateInfoDTO商品编辑更新数据传输对象", description = "用于商品信息更新时获取商品信息")
public class PmsProductUpdateInfoDTO extends PmsProductParamDTO {

    // cateParentId
    @ApiModelProperty("商品上级ID")
    private Long cateParentId;

}
