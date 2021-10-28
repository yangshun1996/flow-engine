package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqcxi.flowEngine.common.ActResult;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.constant.CommonConstant;
import com.cqcxi.flowEngine.enety.TaskStatus;
import com.cqcxi.flowEngine.model.TaskHideDto;
import com.cqcxi.flowEngine.service.TaskStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.List;

/**
 * <p>类描述： 流程驳回相关 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/27 16:15  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/27 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@CrossOrigin
@Api(description = "流程驳回相关")
@RestController
@RequestMapping("/act")
@Slf4j
public class RejectController {
    @Autowired
    private TaskStatusService taskStatusService;

    @ApiOperation(value = "隐藏某一节点任务 不能被查询到 不影响其他并行任务", httpMethod = "POST", response = Object.class, notes = "隐藏某一节点任务 不能被查询到 不影响其他并行任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/hide")
    public HttpResp taskHide(
            @RequestBody @Validated TaskHideDto taskHideDto
            ){
        TaskStatus getTaskStatus = taskStatusService.getById(taskHideDto.getTaskId());
        if (getTaskStatus != null){
            return HttpResp.error("请勿重复隐藏");
        }
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setTaskId(taskHideDto.getTaskId());
        taskStatus.setBusinessId(taskHideDto.getBusinessId());
        taskStatus.setHidde(CommonConstant.intTrue);
        taskStatusService.save(taskStatus);
        return HttpResp.success();
    }
    @ApiOperation(value = "显示节点任务 可以被查询到", httpMethod = "POST", response = Object.class, notes = "显示节点任务 可以被查询到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务Id", dataType = "String", paramType = "query",required = false),
    })
    @RequestMapping("/task/show")
    public HttpResp taskShow(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ){
        taskStatusService.removeById(taskId);
        return HttpResp.success();
    }


    @ApiOperation(value = "根据业务Id查询隐藏的任务", httpMethod = "POST", response = Object.class, notes = "根据业务Id查询隐藏的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="businessId", value="业务Id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/task/hide/query")
    public HttpResp taskHideQuery(
            @RequestParam(value = "businessId", defaultValue = "")String businessId
    ){
        if (StrUtil.isBlank(businessId)){
            return HttpResp.param("请传入业务Id");
        }

        List<TaskStatus> tasks = taskStatusService.getByBusinessId(businessId);
        return HttpResp.success(tasks);
    }
}
