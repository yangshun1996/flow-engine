package com.cqcxi.flowEngine.enety;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>类描述： 流程状态控制</p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/26 17:25  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/26 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class ProcdefStatus {

    @TableId("PROCESS_ID")
    private String processId;

    private String status;

    private String publish;

}
