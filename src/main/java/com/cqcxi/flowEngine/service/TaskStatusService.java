package com.cqcxi.flowEngine.service;

import com.cqcxi.flowEngine.entity.TaskStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TaskStatusService extends IService<TaskStatus> {

    List<TaskStatus> getByBusinessId(String businessId);
}
