package com.alvin.mall.modules.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alvin.mall.modules.pms.dto.PmsProductConditionDTO;
import com.alvin.mall.modules.pms.dto.PmsProductParamDTO;
import com.alvin.mall.modules.pms.dto.PmsProductUpdateInfoDTO;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.alvin.mall.modules.pms.mapper.PmsProductMapper;
import com.alvin.mall.modules.pms.model.PmsProductAttributeValue;
import com.alvin.mall.modules.pms.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-05-31
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {

    @Autowired
    PmsProductMapper productMapper;

    @Autowired
    PmsMemberPriceService memberPriceService;

    @Autowired
    PmsProductAttributeValueService attributeValueService;

    @Autowired
    PmsProductFullReductionService fullReductionService;

    @Autowired
    PmsProductLadderService ladderService;

    @Autowired
    PmsSkuStockService skuStockService;


    @Override
    public Page<PmsProduct> getList(PmsProductConditionDTO productConditionDTO) {
        /**
         *      private String keyword;
         *          private Integer pageNum;
         *          private Integer pageSize;
         *      private Long publishStatus;
         *      private Long verifyStatus;
         *      private String productSn;
         *      private Long productCategoryId;
         *      private Long brandId;
         */

        Page<PmsProduct> page = new Page<>(productConditionDTO.getPageNum(), productConditionDTO.getPageSize());

        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsProduct> lambda = queryWrapper.lambda();
        if (!StrUtil.isBlank(productConditionDTO.getKeyword())) {
            lambda.like(PmsProduct::getName, productConditionDTO.getKeyword());
        }
        if (productConditionDTO.getPublishStatus() != null) {
            lambda.eq(PmsProduct::getPublishStatus, productConditionDTO.getPublishStatus());
        }
        if (productConditionDTO.getVerifyStatus() != null) {
            lambda.eq(PmsProduct::getVerifyStatus, productConditionDTO.getVerifyStatus());
        }
        if (!StrUtil.isBlank(productConditionDTO.getProductSn())) {
            lambda.like(PmsProduct::getProductSn, productConditionDTO.getProductSn());
        }
        if (productConditionDTO.getProductCategoryId() != null) {
            lambda.eq(PmsProduct::getProductCategoryId, productConditionDTO.getProductCategoryId());
        }
        if (productConditionDTO.getBrandId() != null) {
            lambda.eq(PmsProduct::getBrandId, productConditionDTO.getBrandId());
        }
        lambda.orderByDesc(PmsProduct::getSort);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean updateByPublishStatus(List<Long> ids, Long publishStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProduct::getPublishStatus, publishStatus)
                .in(PmsProduct::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateByNewStatus(List<Long> ids, Long newStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProduct::getNewStatus, newStatus)
                .in(PmsProduct::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean updateByRecommendStatus(List<Long> ids, Long recommendStatus) {
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(PmsProduct::getRecommandStatus, recommendStatus)
                .in(PmsProduct::getId, ids);
        return this.update(updateWrapper);
    }

    @Override
    @Transactional
    public boolean saveAll(PmsProductParamDTO productParamDTO) {

        // 保存商品信息
        PmsProduct product = productParamDTO;
        boolean res = this.save(product);

        // 根据优惠方式保存对应优惠表
        boolean res2 = false;
        if (res) {
            // promotionType:促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
            switch(productParamDTO.getPromotionType()) {
                case 0:
                case 1:
                    res2 = true;
                    break;
                case 2:
                    res2 = saveManyList(productParamDTO.getMemberPriceList(), productParamDTO.getId(), memberPriceService);
                    break;
                case 3:
                    res2 = saveManyList(productParamDTO.getProductLadderList(), productParamDTO.getId(), ladderService);
                    break;
                case 4:
                    res2 = saveManyList(productParamDTO.getProductFullReductionList(), productParamDTO.getId(), fullReductionService);
                    break;
                default:
                    break;
            }
        }
        // 保存商品属性相关信息(spu)
        boolean res3 = saveManyList(productParamDTO.getProductAttributeValueList(), productParamDTO.getId(), attributeValueService);
        // 保存商品sku信息
        boolean res4 = saveManyList(productParamDTO.getSkuStockList(), productParamDTO.getId(), skuStockService);
        return res2 && res3 && res4;

    }

    @Override
    public PmsProductUpdateInfoDTO getProduct(Long id) {
        return productMapper.getProduct(id);
    }

    @Override
    @Transactional
    public boolean updateAll(Long productId, PmsProductParamDTO productParamDTO) {
        // 更新商品表
        UpdateWrapper<PmsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(PmsProduct::getId, productId);
        boolean res = this.update(productParamDTO, updateWrapper);
        // 更新商品表的从表（先删除，再插入）
        boolean res2 = false;
        if (res) {
            // promotionType:促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购
            switch(productParamDTO.getPromotionType()) {
                case 0:
                case 1:
                    res2 = true;
                    break;
                case 2:
                    res2 = deleteManyList(productId, memberPriceService);
                    if (res2) {
                        res2 = saveManyList(productParamDTO.getMemberPriceList(), productParamDTO.getId(), memberPriceService);
                    }
                    break;
                case 3:
                    res2 = deleteManyList(productId, ladderService);
                    if (res) {
                        res2 = saveManyList(productParamDTO.getProductLadderList(), productParamDTO.getId(), ladderService);
                    }
                    break;
                case 4:
                    res2 = deleteManyList(productId, fullReductionService);
                    if (res2) {
                        res2 = saveManyList(productParamDTO.getProductFullReductionList(), productParamDTO.getId(), fullReductionService);
                    }
                    break;
                default:
                    break;
            }
        }
        // 更新商品属性相关信息(spu)
        boolean res3 = deleteManyList(productId, attributeValueService);

        if (res3) {
            res3 = saveManyList(productParamDTO.getProductAttributeValueList(), productParamDTO.getId(), attributeValueService);
        }

        // 更新商品sku信息
        boolean res4 = deleteManyList(productId, skuStockService);

        if (res4) {
            res4 = saveManyList(productParamDTO.getSkuStockList(), productParamDTO.getId(), skuStockService);
        }

        System.out.println(res2);
        return res2 && res3 && res4;
    }

    /**
     * 公共方法，用于添加商品信息的从表记录
     * @return 布尔值
     */
    public boolean saveManyList(List<?> list, Long productId, IService service) {

        // 给从表添加商品id（外键）
        if (CollectionUtil.isNotEmpty(list)) {
            try {
                Object o = list.get(0);
                Method setProductId = o.getClass().getMethod("setProductId", Long.class);
                // 批量给每一个对象赋值：productId
                for (Object obj : list) {
                    setProductId.invoke(obj, productId);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // 保存到数据库
        return service.saveBatch(list);

    }

    /**
     * 公共方法：删除商品从表信息，根据商品id
     * @return
     */
    public boolean deleteManyList(Long productId, IService service) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        // 删除之前要先查寻有没有，否则没有数据删除的时候返回的是false，会导致之后的步骤无法进行
        int count = service.count(queryWrapper);
        if (count == 0) return true;
        return service.remove(queryWrapper);
    }

}
