package com.alvin.mall.dto;

import com.alvin.mall.modules.sms.model.SmsHomeAdvertise;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "首页导航栏menus和banners")
public class HomeMenusBannersDTO {

    List<HomeMenusDTO> homeMenusDTOList;

    List<SmsHomeAdvertise> homeAdvertiseList;

}
