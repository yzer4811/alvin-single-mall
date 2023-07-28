package com.alvin.mall.modules.oms.service.impl;

import com.alvin.mall.common.api.ResultCode;
import com.alvin.mall.common.exception.Asserts;
import com.alvin.mall.dto.AddCarDTO;
import com.alvin.mall.dto.CartItemStockDTO;
import com.alvin.mall.modules.oms.model.OmsCartItem;
import com.alvin.mall.modules.oms.mapper.OmsCartItemMapper;
import com.alvin.mall.modules.oms.service.OmsCartItemService;
import com.alvin.mall.modules.pms.model.PmsProduct;
import com.alvin.mall.modules.pms.model.PmsSkuStock;
import com.alvin.mall.modules.pms.service.PmsProductService;
import com.alvin.mall.modules.pms.service.PmsSkuStockService;
import com.alvin.mall.modules.ums.model.UmsMember;
import com.alvin.mall.modules.ums.service.UmsMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author Alvin
 * @since 2023-06-12
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements OmsCartItemService {

    @Autowired
    UmsMemberService memberService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductService productService;
    @Autowired
    OmsCartItemMapper cartItemMapper;

    @Override
    public Boolean add(AddCarDTO addCarDTO) {
        OmsCartItem omsCartItem = new OmsCartItem();
        BeanUtils.copyProperties(addCarDTO,omsCartItem);
        UmsMember currentMember = memberService.getCurrentMember();
        omsCartItem.setMemberId(currentMember.getId());

        // 判断同一个商品、sku、用户 下是否添加的重复的购物车
        OmsCartItem cartItem = getCartItem(omsCartItem.getProductId(), omsCartItem.getProductSkuId(), omsCartItem.getMemberId());
        // 新增
        if(cartItem==null) {
            omsCartItem.setMemberNickname(currentMember.getNickname());

            // 查询sku
            PmsSkuStock sku = skuStockService.getById(omsCartItem.getProductSkuId());
            if (sku == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
            omsCartItem.setPrice(sku.getPrice());
            omsCartItem.setSp1(sku.getSp1());
            omsCartItem.setSp2(sku.getSp2());
            omsCartItem.setSp3(sku.getSp3());
            omsCartItem.setProductPic(sku.getPic());
            omsCartItem.setProductSkuCode(sku.getSkuCode());


            PmsProduct product = productService.getById(omsCartItem.getProductId());
            if (product == null) Asserts.fail(ResultCode.VALIDATE_FAILED);
            omsCartItem.setProductName(product.getName());
            omsCartItem.setProductBrand(product.getBrandName());
            omsCartItem.setProductSn(product.getProductSn());
            omsCartItem.setProductSubTitle(product.getSubTitle());
            omsCartItem.setProductCategoryId(product.getProductCategoryId());

            omsCartItem.setCreateDate(new Date());
            omsCartItem.setModifyDate(new Date());
            return baseMapper.insert(omsCartItem)>0;
        }
        // 修改  给商品数量+1
        else {
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItem.setModifyDate(new Date());

            UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .set(OmsCartItem::getQuantity,cartItem.getQuantity())
                    .set(OmsCartItem::getModifyDate,cartItem.getModifyDate())
                    .eq(OmsCartItem::getId,cartItem.getId());
            return baseMapper.update(cartItem,updateWrapper)>0;
        }
    }

    @Override
    public Integer getProductsSum() {

        UmsMember umsMember = memberService.getCurrentMember();
        if (umsMember != null) {
            QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .select("SUM(quantity) AS count")
                    .lambda()
                    .eq(OmsCartItem::getMemberId, umsMember.getId());

            List<Object> list = baseMapper.selectObjs(queryWrapper);
            if (list != null && list.size() == 1) {
                if (list.get(0) == null) {
                    return 0;
                }
                return Integer.parseInt(list.get(0).toString());
            }

        }
        return 0;
    }

    @Override
    public List<OmsCartItem> getCar() {

        UmsMember currentMember = memberService.getCurrentMember();
        if (currentMember == null) return null;

        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsCartItem::getMemberId, currentMember.getId())
                .eq(OmsCartItem::getDeleteStatus, 0);

        return list(queryWrapper);
    }

    @Override
    public List<CartItemStockDTO> getCarWithStock() {
        UmsMember currentMember = memberService.getCurrentMember();
        return cartItemMapper.getCartItemStock(currentMember.getId());
    }


    @Override
    public Boolean updateQuantity(Long id, Integer quantity) {

        UpdateWrapper<OmsCartItem> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(OmsCartItem::getQuantity, quantity)
                .eq(OmsCartItem::getId, id);
        return update(updateWrapper);
    }

    @Override
    public Boolean deleteCar(Long id) {

        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsCartItem::getId, id);
        return remove(queryWrapper);
    }

    public OmsCartItem getCartItem(Long productId,Long skuId, Long memberId){
        QueryWrapper<OmsCartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(OmsCartItem::getProductId,productId)
                .eq(OmsCartItem::getProductSkuId,skuId)
                .eq(OmsCartItem::getMemberId,memberId);

        return baseMapper.selectOne(queryWrapper);
    }

}
