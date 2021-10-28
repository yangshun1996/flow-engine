package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.date.DateUtil;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.constant.CommonConstant;
import com.cqcxi.flowEngine.enety.TaskEntrust;
import com.cqcxi.flowEngine.model.EntrustCreateDto;
import com.cqcxi.flowEngine.model.TaskHideDto;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.TaskEntrustService;
import com.cqcxi.flowEngine.util.TimeHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "创建委托任务", httpMethod = "POST", response = Object.class, notes = "创建委托任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/create")
    public HttpResp create(
        @RequestBody @Validated EntrustCreateDto entrustCreateDto
    ){
        TaskEntrust taskEntrust = new TaskEntrust();
        taskEntrust.setUserId(entrustCreateDto.getUserId());
        taskEntrust.setAssigneeId(entrustCreateDto.getAssigneeId());
        taskEntrust.setTaskId(entrustCreateDto.getTaskId());
        //校验时间格式
        taskEntrust.setStartTime(TimeHelper.isValidBirth(entrustCreateDto.getStartTime()) ? TimeHelper.stringToDate(entrustCreateDto.getStartTime()) : null);
        taskEntrust.setEndTime(TimeHelper.isValidBirth(entrustCreateDto.getEndTime()) ? TimeHelper.stringToDate(entrustCreateDto.getEndTime()) : null);
        taskEntrust.setStatus(TimeHelper.isValidBirth(entrustCreateDto.getStartTime()) ? CommonConstant.intFalse : CommonConstant.intTrue);
        taskEntrustService.save(taskEntrust);

        //没设置开始时间 直接转交任务


        return HttpResp.success();
    }
}
