package com.cqcxi.flowEngine.schedule;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqcxi.flowEngine.constant.CommonConstant;
import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.util.TimeHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * <p>类描述： 任务委托转交 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/29 10:40  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/29 </p>
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

    @Scheduled(cron = "*/10 * * * * ?")
    public void configureTasks() {

    }

}
