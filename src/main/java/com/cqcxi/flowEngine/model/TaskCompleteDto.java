package com.cqcxi.flowEngine.model;

import com.cqcxi.flowEngine.exception.ValidList;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>类描述： 完成任务 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/27 14:18  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/27 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskCompleteDto {

    @NotBlank(message = "任务Id不能为空")
    private String taskId;

    @NotBlank(message = "用户Id不能为空")
    private String userId;

    @Valid
    private List<TaskParam> taskParams;
}
