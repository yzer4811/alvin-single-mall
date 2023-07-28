package com.alvin.mall.controller;

import com.alvin.mall.common.api.CommonResult;
import com.alvin.mall.dto.ConfirmOrderDTO;
//import com.alvin.mall.dto.OrderDetailDTO;
//import com.alvin.mall.dto.OrderListDTO;
//import com.alvin.mall.dto.OrderParamDTO;
import com.alvin.mall.dto.OrderDetailDTO;
import com.alvin.mall.dto.OrderListDTO;
import com.alvin.mall.dto.OrderParamDTO;
import com.alvin.mall.modules.oms.model.OmsOrder;
import com.alvin.mall.modules.oms.service.OmsOrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Api(tags = "OrderController",description = "订单服务接口")
@RequestMapping("/order")
@Slf4j
public class OrderController {


    @Autowired
    OmsOrderService orderService;

    /**
//     *  加入购物车---生成确认订单实现
//     *  立即购买—生成确认订单实现 product_id  sku_id. 改成DTO接收
//     *    复用业务逻辑的代码 product_id 和sku_id 查出购物车对象所需要信息
//     *  初始化确认订单的商品和收货地址信息
     * this.axios.post('/order/generateConfirmOrder',Qs.stringify({itemIds: constStore.itemids}
     */
    @RequestMapping(value="generateConfirmOrder",method = RequestMethod.POST)
    public CommonResult generateConfirmOrder(@RequestParam("itemIds") List<Long> ids){
         ConfirmOrderDTO confirmOrderDTO= orderService.generateConfirmOrder(ids);
         return CommonResult.success(confirmOrderDTO) ;
    }

    /**
     *  生成订单(下单）
     * this.axios
     *           .post("/order/generateOrder", {
     */
    @RequestMapping(value="/generateOrder",method = RequestMethod.POST)
    public CommonResult generateOrder(@RequestBody OrderParamDTO paramDTO){
        OmsOrder omsOrder = orderService.generateOrder(paramDTO);
        return CommonResult.success(omsOrder.getId());
    }

    /**
     *  读取下单成功后的订单详情
     * this.axios.get(`/order/orderDetail?orderId=${this.orderId}`).then((res)=>{
     */
    @RequestMapping(value="/orderDetail")
    public CommonResult getOrderDetail(@RequestParam("orderId")Long id){
        OrderDetailDTO dto=orderService.getOrderDetail(id);
        return  CommonResult.success(dto);
    }

    /**
     *  我的订单列表
     * this.axios.post('/order/list/userOrder',Qs.stringify({
     pageSize:10,
     pageNum:this.pageNum
     */
    @RequestMapping(value="/list/userOrder",method = RequestMethod.POST)
    public CommonResult getMyOrders(
            @RequestParam(value="pageSize",defaultValue = "10")Integer pageSize,
            @RequestParam(value="pageNum",defaultValue = "1")Integer pageNum) {

        IPage<OrderListDTO> myOrders = orderService.getMyOrders(pageSize, pageNum);
        return CommonResult.success(myOrders);
    }

}
