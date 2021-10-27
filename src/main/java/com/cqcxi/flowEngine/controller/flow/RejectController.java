package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.util.StrUtil;
import com.cqcxi.flowEngine.common.ActResult;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.service.TaskStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "隐藏某一节点任务 不影响其他并行任务", httpMethod = "POST", response = Object.class, notes = "隐藏某一节点任务 不影响其他并行任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", value="任务Id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/task/hide")
    public HttpResp form(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ){
        if (StrUtil.isBlank(taskId)){
            return HttpResp.param("请传入任务Id");
        }

        return HttpResp.success();
    }
}
