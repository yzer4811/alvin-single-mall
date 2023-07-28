package com.alvin.mall.controller;

import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.dto.AddCarDTO;
//import com.alvin.mall.dto.CartItemStockDTO;
import com.alvin.mall.dto.CartItemStockDTO;
import com.alvin.mall.modules.oms.model.OmsCartItem;
import com.alvin.mall.modules.oms.service.OmsCartItemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Api(tags = "CarController",description = "购物车服务接口")
@RequestMapping("/car")
public class CarController {

    @Autowired
    OmsCartItemService cartItemService;

    /**
     *  .post("/cart/add", {
     *           productId: this.id,
     *           productSkuId: this.skuId,
     *           quantity: 1,
     *         })
     * @return
     */
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public CommonResult addProduct(@RequestBody AddCarDTO addCarDTO){
        System.out.println(addCarDTO);
        Boolean result=cartItemService.add(addCarDTO);
        if(result){
            return  CommonResult.success(result);
        }
        else {
            return  CommonResult.failed();
        }
    }

    /**
     * 获取当前用户购物车商品数量
     * axios.get('/car/products/sum')
     */
    @GetMapping("/products/sum")
    public CommonResult getProductsSum() {
        Integer count = cartItemService.getProductsSum();
        if (count != null) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    /**
     * 获取当前用户购物车列表
     * axios.get('/car/list')
     */
    @GetMapping("/list")
    public CommonResult<List<CartItemStockDTO>> getCarList() {

        List<CartItemStockDTO> list = cartItemService.getCarWithStock();
        if (list != null) {
            return CommonResult.success(list);
        }
        return CommonResult.failed();
    }

    /**
     * 添加商品购买数量
     * axios.post('/car/update/quantity',Qs.stringify({
     *               id: item.id,
     *               quantity: item.quantity,
     *             }),
     */
    @PostMapping("/update/quantity")
    public CommonResult updateQuantity(
            @RequestParam("id") Long id,
            @RequestParam("quantity") Integer quantity) {

        Boolean res = cartItemService.updateQuantity(id, quantity);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }

    /**
     * 删除购物车商品
     * axios.post('/car/delete',Qs.stringify({
     *             ids: item.id,
     *           })
     */
    @PostMapping("/delete")
    public CommonResult deleteCar(@RequestParam("ids") Long ids) {

        Boolean res = cartItemService.deleteCar(ids);
        if (res) {
            return CommonResult.success(res);
        }
        return CommonResult.failed();
    }
}
