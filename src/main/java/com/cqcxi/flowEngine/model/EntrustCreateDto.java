package com.cqcxi.flowEngine.model;

import cn.hutool.crypto.digest.mac.MacEngine;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>类描述： 委托任务 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/29 17:57  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/29 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@ApiModel("委托任务")
@Data
public class EntrustCreateDto {

    @ApiModelProperty("原代办人Id")
    @NotBlank(message = "请传入原代办人Id")
    private String originalAssigneeId;

    @ApiModelProperty("代办人Id")
    @NotBlank(message = "请输入代办人Id")
    private String assigneeId;

    @ApiModelProperty("任务模式： 1.每天任务 2.周任务 3.月任务")
    @Max(value = 3,message = "类型最大为3")
    @Min(value = 1,message = "类型最小为1 ")
    private Integer type;

    @ApiModelProperty("开始日期")
    private String startDate;

    @ApiModelProperty("结束日期")
    private String endDate;

    @ApiModelProperty("开始时间")
    @NotBlank(message = "请传入开始时间或时间格式不正确")
    private String startHour;

    @ApiModelProperty("结束时间")
    @NotBlank(message = "请传入结束时间或时间格式不正确")
    private String endHour;

    @ApiModelProperty("星期数")
    private Integer[] dayOfWeek;


    public void setStartHour(String startHour) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            sdf.parse(startHour);
            this.startHour = startHour;
        } catch (ParseException e) {
            this.startHour = null;
        }
    }

    public void setEndHour(String endHour) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            sdf.parse(endHour);
            this.endHour = endHour;
        } catch (ParseException e) {
            this.endHour = null;
        }
    }
}
