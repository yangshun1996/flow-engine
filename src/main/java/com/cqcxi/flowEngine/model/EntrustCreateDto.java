package com.cqcxi.flowEngine.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>类描述： 创建委托任务 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/28 16:22  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/28 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class EntrustCreateDto {

    //用户Id
    @NotBlank(message = "请输入用户Id")
    private String userId;

    //代办人Id
    @NotBlank(message = "请输入代办人Id")
    private String assigneeId;

    //任务Id
    @NotBlank(message = "请传入任务Id")
    private String taskId;

    //委托开始时间
    private String startTime;

    //委托结束时间
    private String endTime;
}
