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
import org.springframework.stereotype.Service;

import java.util.*;

/**
 *
 */
@Service
public class TaskEntrustServiceImpl extends ServiceImpl<TaskEntrustMapper, TaskEntrust>
    implements TaskEntrustService{

    @Override
    public void createTaskEntrust(EntrustCreateDto entrustCreateDto) {
        //cron工具类
        TaskScheduleModel taskScheduleModel = new TaskScheduleModel();
        taskScheduleModel.setJobType(entrustCreateDto.getType());

        //开始时间设置cron时分秒
        String startHour = entrustCreateDto.getStartHour();
        String[] split = startHour.split(":");
        taskScheduleModel.setHour(Integer.valueOf(split[0]));
        taskScheduleModel.setMinute(Integer.valueOf(split[1]));
        taskScheduleModel.setSecond(Integer.valueOf(split[2]));

        //星期
        taskScheduleModel.setDayOfWeeks(entrustCreateDto.getDayOfWeek());

        //日期
        DateTime startDate = DateUtil.parse(entrustCreateDto.getStartDate(), "yyyy-MM-dd");
        DateTime endDate = DateUtil.parse(entrustCreateDto.getEndDate(), "yyyy-MM-dd");

        String cronExpression = "";
        String description = "";

        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.DATE.getType()) {
            //获取相差日期
            HashMap<Integer, List<Integer>> detHashList = TimeHelper.getDetHashList(entrustCreateDto.getStartDate(), entrustCreateDto.getEndDate());
            Set<Map.Entry<Integer, List<Integer>>> entries = detHashList.entrySet();
            for (Map.Entry<Integer, List<Integer>> entry : entries) {
                Integer month = entry.getKey();
                List<Integer> days = entry.getValue();
                taskScheduleModel.setMonth(month);
                Integer[] daysArray = new Integer[days.size()];
                days.toArray(daysArray);
                taskScheduleModel.setDayOfMonths(daysArray);
                String dateCronExpression = CronUtil.createCronExpression(taskScheduleModel);
                String dateDescription = CronUtil.createDescription(taskScheduleModel);
                if ("".equals(cronExpression)){
                    cronExpression += dateCronExpression;
                }else {
                    cronExpression += "|" + dateCronExpression;
                }
            }
        }else {
            //生成cron
            cronExpression = CronUtil.createCronExpression(taskScheduleModel);
            description = CronUtil.createDescription(taskScheduleModel);
        }


        TaskEntrust taskEntrust = new TaskEntrust();
        taskEntrust.setAssigneeId(entrustCreateDto.getAssigneeId());
        taskEntrust.setOriginalAssigneeId(entrustCreateDto.getOriginalAssigneeId());
        taskEntrust.setCron(cronExpression);
        taskEntrust.setCronNote(description);
        taskEntrust.setDayOfWeek(ArrayUtil.join(entrustCreateDto.getDayOfWeek(),","));
        taskEntrust.setStartDate(startDate);
        taskEntrust.setEndDate(endDate);
        taskEntrust.setStartHour(DateUtil.parse(entrustCreateDto.getStartHour(), "HH:mm:ss"));
        taskEntrust.setEndHour(DateUtil.parse(entrustCreateDto.getEndHour(), "HH:mm:ss"));
        taskEntrust.setType(entrustCreateDto.getType());

        getBaseMapper().insert(taskEntrust);
    }

}




