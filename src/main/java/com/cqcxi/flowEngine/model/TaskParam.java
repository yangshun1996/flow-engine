package com.cqcxi.flowEngine.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>类描述： 任务携带参数 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 14:37  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskParam {

    @NotBlank(message = "参数名不能为空")
    private String name;

    @NotBlank(message = "参数不能为空")
    private String val;
}
