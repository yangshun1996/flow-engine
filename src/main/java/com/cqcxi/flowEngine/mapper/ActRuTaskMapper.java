package com.cqcxi.flowEngine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqcxi.flowEngine.enety.ActRuTask;
import com.cqcxi.flowEngine.model.TaskQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>类描述： 任务表 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/26 17:27  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/26 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
public interface ActRuTaskMapper extends BaseMapper<ActRuTask> {
    List<TaskQueryVo> queryTask(@Param("procInstId") String procInstId);

    String queryStarterByTaskId(@Param("taskId") String taskId);
}
