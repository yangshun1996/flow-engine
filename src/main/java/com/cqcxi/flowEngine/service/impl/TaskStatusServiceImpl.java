package com.cqcxi.flowEngine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqcxi.flowEngine.entity.TaskStatus;
import com.cqcxi.flowEngine.service.TaskStatusService;
import com.cqcxi.flowEngine.mapper.TaskStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TaskStatusServiceImpl extends ServiceImpl<TaskStatusMapper, TaskStatus>
    implements TaskStatusService{

    @Autowired
    private TaskStatusMapper taskStatusMapper;


    @Override
    public List<TaskStatus> getByBusinessId( String businessId) {
        List<TaskStatus> byBusinessId = taskStatusMapper.getByBusinessId(businessId);
        return byBusinessId;
    }
}




