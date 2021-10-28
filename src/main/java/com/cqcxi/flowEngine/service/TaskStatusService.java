package com.cqcxi.flowEngine.service;

import com.cqcxi.flowEngine.enety.TaskStatus;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface TaskStatusService extends IService<TaskStatus> {

    List<TaskStatus> getByBusinessId(String businessId);
}
