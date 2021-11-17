package com.cqcxi.flowEngine.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.cron.Scheduler;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqcxi.flowEngine.constant.CommonConstant;
import com.cqcxi.flowEngine.entity.ActRuTask;
import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.cqcxi.flowEngine.service.ActRuTaskService;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.util.CronUtil;
import com.cqcxi.flowEngine.util.TaskScheduleModel;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.calendar.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;


/**
 * <p>类描述： 任务委托转交 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/29 10:40  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/11/04 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Configuration      // 1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class TaskEntrustSchedule {
    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private TaskEntrustService taskEntrustService;

    @Autowired
    private ActRuTaskService actRuTaskService;

    @Scheduled(cron = "*/10 * * * * ?")
    public void configureTasks() {

        QueryWrapper<TaskEntrust> wrapper = new QueryWrapper<TaskEntrust>();
        wrapper.eq("STATUS", CommonConstant.intTrue);

        List<TaskEntrust> tasks = taskEntrustService.list(wrapper);
        for (TaskEntrust task : tasks) {
            entrust(task);
        }
    }

    /**
     * 执行任务
     */
    void entrust(TaskEntrust task){
        //当前时间
        DateTime date = DateUtil.date();

        if (task == null){
            return;
        }

        //是否今天执行
        if (!isToday(task, date)){
            return;
        }

        //当前日期
        String currentDate = date.toDateStr();

        //判断当前时间是否在任务时间内
        String strStartHour = new DateTime(task.getStartHour()).toTimeStr();
        String strEndHour = new DateTime(task.getEndHour()).toTimeStr();
        DateTime taskStartTime = new DateTime(currentDate + " " + strStartHour);
        DateTime taskEndTime = new DateTime(currentDate + " " + strEndHour);
        if (DateUtil.between(date, taskStartTime, DateUnit.MS, false) > 0 ||
            DateUtil.between(date, taskEndTime, DateUnit.MS, false) < 0
        ){
            return;
        }

        //当前任务人是否有委托任务
        QueryWrapper<ActRuTask> wrapper = new QueryWrapper<>();
        wrapper.eq("ASSIGNEE_", task.getOriginalAssigneeId());
        List<ActRuTask> actRuTasks = actRuTaskService.list(wrapper);
        if (actRuTasks.isEmpty()){
            return;
        }

        //当前星期数
        int dayOfWeek = DateUtil.thisDayOfWeek();
        dayOfWeek = dayOfWeek == 1 ? 7 : dayOfWeek - 1;

        //TODO (暂定)优先等级：天<周<日期，当天存在日期定时时按照日期执行其他定时不执行;当天存在周定时时按照周执行，天不在执行
        Integer taskType = task.getType();
        if (taskType == TaskScheduleModel.JobType.DAY.getType()){
            Boolean exist = taskEntrustService.weekTaskExist(task.getOriginalAssigneeId(), String.valueOf(dayOfWeek), null, null);
            if (exist){
                log.info("{}当天存在[星期委托任务],停止此次[每天委托任务] 任务Id:{}", task.getOriginalAssigneeId(), task.getId());
                return;
            }
            exist = taskEntrustService.dateTaskExist(task.getOriginalAssigneeId(), currentDate, currentDate, null, null);
            if (exist){
                log.info("{}当天存在[日期委托任务],停止此次[每天委托任务] 任务Id:{}", task.getOriginalAssigneeId(), task.getId());
                return;
            }
        }
        else if (taskType == TaskScheduleModel.JobType.WEEK.getType()){
            Boolean exist = taskEntrustService.dateTaskExist(task.getOriginalAssigneeId(), currentDate, currentDate, null, null);
            if (exist){
                log.info("{}当天存在[日期委托任务],停止此次[星期委托任务] 任务Id:{}", task.getOriginalAssigneeId(), task.getId());
                return;
            }
        }

        //任务转交
        for (ActRuTask actRuTask : actRuTasks) {
            activitiService.entrustTask(actRuTask.getId(), task.getAssigneeId());
        }
        log.info("执行任务委托,任务Id:{} 任务类型:{} 转交任务:{}" , task.getId(), task.getType() == TaskScheduleModel.JobType.DAY.getType() ? TaskScheduleModel.JobType.DAY.getMessage() :
                task.getType() == TaskScheduleModel.JobType.WEEK.getType() ? TaskScheduleModel.JobType.WEEK.getMessage() : TaskScheduleModel.JobType.DATE.getMessage(),
                actRuTasks.toString());
    }

    /**
     * 判断任务是否今天执行
     */
    boolean isToday(TaskEntrust task, DateTime date){
        //天任务
        if (task.getType() == TaskScheduleModel.JobType.DAY.getType()){
            return true;
        }
        //周任务
        else if (task.getType() == TaskScheduleModel.JobType.WEEK.getType()){
            int dayOfWeek = DateUtil.thisDayOfWeek();
            dayOfWeek = dayOfWeek == 1 ? 7 : dayOfWeek - 1;
            String daysOfWeek = task.getDayOfWeek();
            String[] daysOfWeekArr = daysOfWeek.split(",");
            if (ArrayUtil.contains(daysOfWeekArr, String.valueOf(dayOfWeek))){
                return true;
            }
        }
        //日期任务
        else if (task.getType() == TaskScheduleModel.JobType.DATE.getType()){
            if (DateUtil.between(date, task.getStartDate(), DateUnit.MS, false) < 0  &&
                DateUtil.between(date, task.getEndDate(), DateUnit.MS, false) > 0
            ){
                return true;
            }
        }
        return false;
    }

}
