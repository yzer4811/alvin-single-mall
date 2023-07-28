package com.alvin.mall.modules.oms.dto;

import com.alvin.mall.modules.oms.model.OmsOrder;
import com.alvin.mall.modules.oms.model.OmsOrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OmsOrderInfoDTO订单信息数据传输对象", description="用于订单信息的接收")
public class OmsOrderInfoDTO extends OmsOrder {

    @ApiModelProperty("订单中商品的信息")
    private List<OmsOrderItem> orderItemList;

}
