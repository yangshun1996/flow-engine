package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.util.StrUtil;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.model.EntrustCreateDto;
import com.cqcxi.flowEngine.service.ActRuTaskService;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.util.TaskScheduleModel;
import com.cqcxi.flowEngine.util.TimeHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

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

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private ActRuTaskService actRuTaskService;


    @ApiOperation(value = "创建委托任务", httpMethod = "POST", response = Object.class, notes = "创建委托任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/create")
    public HttpResp taskCreate(
            @RequestBody @Validated EntrustCreateDto entrustCreateDto
    ){
        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.WEEK.getType()){
            if (entrustCreateDto.getDayOfWeek() == null || entrustCreateDto.getDayOfWeek().length == 0){
                return HttpResp.param("请传入星期");
            }
        }
        if (entrustCreateDto.getType() == TaskScheduleModel.JobType.DATE.getType()){
            if (StrUtil.isBlank(entrustCreateDto.getStartDate()) || !TimeHelper.isValidDate(entrustCreateDto.getStartDate())){
                return HttpResp.param("请传入开始日期");
            }
            if (StrUtil.isBlank(entrustCreateDto.getEndDate()) || !TimeHelper.isValidDate(entrustCreateDto.getEndDate())){
                return HttpResp.param("请传入结束日期");
            }
        }

        //创建任务
        taskEntrustService.createTaskEntrust(entrustCreateDto);
        return HttpResp.success();
    }


}
