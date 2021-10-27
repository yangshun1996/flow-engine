package com.cqcxi.flowEngine.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>类描述：启动流程 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/27 13:36  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/27 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskStartDto {

    @NotBlank(message = "请传入流程Id")
    private String processId;

    @NotBlank(message = "请传入用户Id")
    private String userId;

    @NotBlank(message = "请传入业务Id")
    private String businessId;
}
