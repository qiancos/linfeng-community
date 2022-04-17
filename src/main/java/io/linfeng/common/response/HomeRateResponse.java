package io.linfeng.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 主页统计数据对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HomeRateResponse对象", description="主页统计数据对象")
public class HomeRateResponse implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "今日销售额")
    private Object sales;

    @ApiModelProperty(value = "昨日销售额")
    private Object yesterdaySales;

    @ApiModelProperty(value = "今日访问量")
    private Object pageviews;

    @ApiModelProperty(value = "昨日访问量")
    private Object yesterdayPageviews;

    @ApiModelProperty(value = "今日订单量")
    private Object orderNum;

    @ApiModelProperty(value = "昨日订单量")
    private Object yesterdayOrderNum;

    @ApiModelProperty(value = "今日新增用户")
    private Object newUserNum;

    @ApiModelProperty(value = "昨日新增用户")
    private Object yesterdayNewUserNum;


}
