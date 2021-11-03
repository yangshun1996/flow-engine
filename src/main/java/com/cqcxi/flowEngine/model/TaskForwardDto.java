package com.cqcxi.flowEngine.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>类描述：任务转交 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/11/1 14:12  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/11/1 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskForwardDto {
    //任务Id
    @NotBlank(message = "请传入任务Id")
    private String taskId;

    //代理人Id
    @NotBlank(message = "请传入代理人Id")
    private String assignee;
}
