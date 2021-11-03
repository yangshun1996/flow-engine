package com.cqcxi.flowEngine.service;

import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqcxi.flowEngine.model.EntrustCreateDto;

/**
 *
 */
public interface TaskEntrustService extends IService<TaskEntrust> {
    void createTaskEntrust(EntrustCreateDto entrustCreateDto);
}
