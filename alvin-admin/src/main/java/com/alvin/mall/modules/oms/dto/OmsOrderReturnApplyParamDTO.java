package com.alvin.mall.modules.oms.dto;

import com.alvin.mall.modules.oms.model.OmsOrderReturnApply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OmsOrderReturnApplyParamDTO订单退货数据传输对象", description="用于订单退货查询信息数据传输")
public class OmsOrderReturnApplyParamDTO {

    private Integer pageNum;

    private Integer pageSize;

    private Long id;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private Integer status;

    @ApiModelProperty(value = "申请时间")
    private String createTime;

    @ApiModelProperty(value = "处理人员")
    private String handleMan;

    @ApiModelProperty(value = "处理时间")
    private String handleTime;

}
