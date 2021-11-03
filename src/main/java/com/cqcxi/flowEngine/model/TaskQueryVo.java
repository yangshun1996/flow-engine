package com.cqcxi.flowEngine.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>类描述： 任务查询 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/27 14:34  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/27 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class TaskQueryVo {

    private String id;

    private String name;

    /*
     * 业务Id
     */
    private String businessId;

    /*
     * 代理人
     */
    private String assignee;

    /*
     * 创建时间
     */
    private String createTime;

    /*
     * 发起人
     */
    private String initiator;
}
