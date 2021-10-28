package com.cqcxi.flowEngine.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>类描述： 隐藏任务 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/27 16:56  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/27 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskHideDto {

    @NotBlank(message = "任务Id不能为空")
    private String taskId;

    @NotBlank(message = "业务Id不能为空")
    private String businessId;
}
