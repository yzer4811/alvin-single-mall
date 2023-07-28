package com.alvin.mall.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "商品信息数据传输对象")
public class ProductDTO {

    private Long id;

    private String name;

    private String pic;

    @ApiModelProperty(value = "商品售价")
    private BigDecimal price;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty("用于判断是否需要显示xx元“起”，0需要，1不需要")
    private Integer sub;

}
