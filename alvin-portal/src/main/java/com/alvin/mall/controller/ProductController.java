package com.alvin.mall.controller;

import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.dto.ProductDetailDTO;
import com.alvin.mall.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    PmsProductService productService;

    /**
     * 获取商品详情
     * get(`/product/detail/${this.id}`)
     */
    @GetMapping(value = "/detail/{id}")
    public CommonResult<ProductDetailDTO> getProductDetail(@PathVariable("id") Long id) {
        ProductDetailDTO productDTO =  productService.getProductDetail(id);
        return CommonResult.success(productDTO);
    }
}
