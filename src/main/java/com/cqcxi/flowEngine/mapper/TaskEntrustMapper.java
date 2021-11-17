package com.cqcxi.flowEngine.mapper;

import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.cqcxi.flowEngine.entity.TaskEntrust
 */
public interface TaskEntrustMapper extends BaseMapper<TaskEntrust> {
    Boolean weekTaskExist(@Param("userId") String userId, @Param("days") String days, @Param("startHour") String startHour, @Param("endHour") String endHour);

    Boolean dateTaskExist(@Param("userId") String userId, @Param("startDate") String start, @Param("endDate") String end, @Param("startHour") String startHour, @Param("endHour") String endHour);

    Boolean dayTaskExist(@Param("userId") String userId, @Param("start") String start, @Param("end") String end);
}




