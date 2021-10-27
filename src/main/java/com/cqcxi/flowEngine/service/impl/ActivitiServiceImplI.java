package com.cqcxi.flowEngine.service.impl;

import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.injector.methods.SelectByMap;
import com.cqcxi.flowEngine.enety.ActRuTask;
import com.cqcxi.flowEngine.mapper.ActRuTaskMapper;
import com.cqcxi.flowEngine.service.IActRuTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述： 任务表 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 16:56  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Service
public class ActivitiServiceImplI extends ServiceImpl<ActRuTaskMapper, ActRuTask> implements IActRuTaskService {

    @Override
    public List<ActRuTask> selectByProcInstId(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("PROC_INST_ID_", id);
        List<ActRuTask> actRuTasks = baseMapper.selectByMap(params);
        return actRuTasks;
    }
}
