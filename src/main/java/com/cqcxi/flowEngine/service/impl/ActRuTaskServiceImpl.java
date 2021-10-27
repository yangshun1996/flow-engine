package com.cqcxi.flowEngine.service.impl;

import com.cqcxi.flowEngine.enety.ActRuTask;
import com.cqcxi.flowEngine.mapper.ActRuTaskMapper;
import com.cqcxi.flowEngine.model.TaskQueryVo;
import com.cqcxi.flowEngine.service.ActRuTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>类描述： 任务表 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 16:56  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Service
public class ActRuTaskServiceImpl extends ServiceImpl<ActRuTaskMapper, ActRuTask> implements ActRuTaskService {

    @Override
    public List<TaskQueryVo> selectByProcInstId(String id) {
        List<TaskQueryVo> actRuTasks = baseMapper.queryTask(id);
        return actRuTasks;
    }
}
