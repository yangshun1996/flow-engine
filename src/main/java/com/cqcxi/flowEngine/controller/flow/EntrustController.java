package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.constant.CommonConstant;
import com.cqcxi.flowEngine.entity.TaskEntrust;
import com.cqcxi.flowEngine.model.EntrustCreateDto;
import com.cqcxi.flowEngine.service.ActRuTaskService;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.util.TaskScheduleModel;
import com.cqcxi.flowEngine.util.TimeHelper;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>类描述： 任务委托 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/28 16:16  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/28 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@CrossOrigin
@Api(description = "任务委托")
@RestController
@RequestMapping("/act/entrust")
@Slf4j
public class EntrustController {
    @Autowired
    private TaskEntrustService taskEntrustService;

    @ApiOperation(value = "创建委托任务", httpMethod = "POST", response = Object.class, notes = "创建委托任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/create")
    public HttpResp taskCreate(
            @RequestBody @Validated EntrustCreateDto entrustCreateDto
    ){

        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.DAY.getType()){
            if (taskEntrustService.dayTaskExist(entrustCreateDto.getOriginalAssigneeId(), entrustCreateDto.getStartHour(), entrustCreateDto.getEndHour())){
                return HttpResp.error("已经存在相同时间任务");
            }
        }
        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.WEEK.getType()){
            if (entrustCreateDto.getDayOfWeek() == null || entrustCreateDto.getDayOfWeek().length == 0){
                return HttpResp.param("请传入星期");
            }
            if (taskEntrustService.weekTaskExist(entrustCreateDto.getOriginalAssigneeId(),
                    ArrayUtil.join(entrustCreateDto.getDayOfWeek(),","),
                    entrustCreateDto.getStartHour(),
                    entrustCreateDto.getEndHour())){
                return HttpResp.error("已经存在相同时间任务");
            }
        }
        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.DATE.getType()){
            if (StrUtil.isBlank(entrustCreateDto.getStartDate()) || !TimeHelper.isValidDate(entrustCreateDto.getStartDate())){
                return HttpResp.param("请传入开始日期");
            }
            if (StrUtil.isBlank(entrustCreateDto.getEndDate()) || !TimeHelper.isValidDate(entrustCreateDto.getEndDate())){
                return HttpResp.param("请传入结束日期");
            }
            if (taskEntrustService.dateTaskExist(entrustCreateDto.getOriginalAssigneeId(),
                    entrustCreateDto.getStartDate(),
                    entrustCreateDto.getEndDate(),
                    entrustCreateDto.getStartHour(),
                    entrustCreateDto.getEndHour())){
                return HttpResp.error("已经存在相同时间任务");
            }
        }

        //创建任务
        taskEntrustService.createTaskEntrust(entrustCreateDto);
        return HttpResp.success();
    }


    @ApiOperation(value = "查询委托", httpMethod = "POST", response = Object.class, notes = "查询委托")
    @ApiImplicitParams({
            @ApiImplicitParam(name="originalAssigneeId", value="原代办人Id 不传则不做判断", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/task/list")
    public HttpResp taskList(
            @RequestParam(value = "originalAssigneeId", defaultValue = "")String originalAssigneeId
    ){
        QueryWrapper<TaskEntrust> wrapper = new QueryWrapper<TaskEntrust>();
        wrapper.eq("STATUS", CommonConstant.intTrue);
        //不传user则不做判断
        if (!StrUtil.isBlank(originalAssigneeId)){
            wrapper.eq("ORIGINAL_ASSIGNEE_ID", originalAssigneeId);
        }
        List<TaskEntrust> taskEntrusts = taskEntrustService.getBaseMapper().selectList(wrapper);
        return HttpResp.success(taskEntrusts);
    }

    @ApiOperation(value = "删除委托", httpMethod = "POST", response = Object.class, notes = "删除委托")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/task/delete")
    public HttpResp taskDelete(
            @RequestParam(value = "id", defaultValue = "")String id
    ){
        if (!NumberUtil.isNumber(id)){
            return HttpResp.param("请传入Id");
        }

        TaskEntrust taskEntrust = new TaskEntrust();
        taskEntrust.setId(Integer.valueOf(id));
        taskEntrust.setStatus(CommonConstant.intFalse);
        taskEntrustService.updateById(taskEntrust);
        return HttpResp.success();
    }
}
