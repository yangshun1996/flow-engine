package com.cqcxi.flowEngine.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.cqcxi.flowEngine.entity.TaskStatus;
import com.cqcxi.flowEngine.model.EntrustCreateDto;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.mapper.TaskEntrustMapper;
import com.cqcxi.flowEngine.util.CronUtil;
import com.cqcxi.flowEngine.util.TaskScheduleModel;
import com.cqcxi.flowEngine.util.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class TaskEntrustServiceImpl extends ServiceImpl<TaskEntrustMapper, TaskEntrust>
    implements TaskEntrustService{

    @Autowired
    private TaskEntrustMapper taskEntrustMapper;

    @Override
    public void createTaskEntrust(EntrustCreateDto entrustCreateDto) {
        //日期
        DateTime startDate = null;
        DateTime endDate = null;

        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.DATE.getType()) {
            startDate = DateUtil.parse(entrustCreateDto.getStartDate(), "yyyy-MM-dd");
            endDate = DateUtil.parse(entrustCreateDto.getEndDate(), "yyyy-MM-dd");
        }

        TaskEntrust taskEntrust = new TaskEntrust();
        taskEntrust.setAssigneeId(entrustCreateDto.getAssigneeId());
        taskEntrust.setOriginalAssigneeId(entrustCreateDto.getOriginalAssigneeId());
        taskEntrust.setDayOfWeek(ArrayUtil.join(entrustCreateDto.getDayOfWeek(),","));
        taskEntrust.setStartDate(startDate);
        taskEntrust.setEndDate(endDate);
        taskEntrust.setStartHour(DateUtil.parse(entrustCreateDto.getStartHour(), "HH:mm:ss"));
        taskEntrust.setEndHour(DateUtil.parse(entrustCreateDto.getEndHour(), "HH:mm:ss"));
        taskEntrust.setType(entrustCreateDto.getType());

        getBaseMapper().insert(taskEntrust);
    }

    @Override
    public Boolean weekTaskExist(String userId, String days, String startHour, String endHour) {
        return taskEntrustMapper.weekTaskExist(userId, days, startHour, endHour);
    }

    @Override
    public Boolean dateTaskExist(String userId, String start, String end, String startHour, String endHour) {
        return taskEntrustMapper.dateTaskExist(userId, start, end, startHour, endHour);
    }

    @Override
    public Boolean dayTaskExist(String userId, String start, String end) {
        Boolean aBoolean = taskEntrustMapper.dayTaskExist(userId, start, end);
        return aBoolean;
    }


}




