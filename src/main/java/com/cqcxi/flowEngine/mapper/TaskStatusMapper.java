package com.cqcxi.flowEngine.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cqcxi.flowEngine.entity.TaskStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 */
public interface TaskStatusMapper extends BaseMapper<TaskStatus> {
    List<TaskStatus> getByBusinessId( @Param("businessId") String businessId);
}




