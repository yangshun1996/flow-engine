package com.cqcxi.flowEngine.service;

import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqcxi.flowEngine.model.EntrustCreateDto;

import java.util.List;

/**
 *
 */
public interface TaskEntrustService extends IService<TaskEntrust> {
    void createTaskEntrust(EntrustCreateDto entrustCreateDto);

    /**
     * 方法描述：判断周任务是否有相同天相同时间
     * 创建人员：杨顺
     * 创建时间： 2021/11/4 16:28
     * 修改人员：
     * 修改内容：
     * 修改时间：
     */
    Boolean weekTaskExist( String userId, String days, String startHour, String endHour);

    /**
     * 方法描述：判断日期任务是否有相同天相同时间
     * 创建人员：杨顺
     * 创建时间： 2021/11/4 16:28
     * 修改人员：
     * 修改内容：
     * 修改时间：
     */
    Boolean dateTaskExist( String userId, String start, String end, String startHour, String endHour);

    /**
     * 方法描述：判断天任务是否有相同时间
     * 创建人员：杨顺
     * 创建时间： 2021/11/4 16:29
     * 修改人员：
     * 修改内容：
     * 修改时间：
     */
    Boolean dayTaskExist( String userId, String start, String end);

}
