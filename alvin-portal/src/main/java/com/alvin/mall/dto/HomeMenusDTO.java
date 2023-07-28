package com.alvin.mall.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "首页导航栏数据")
public class HomeMenusDTO {

    private Long id;

    private String name;

    private List<ProductDTO> productDTOList;

}
