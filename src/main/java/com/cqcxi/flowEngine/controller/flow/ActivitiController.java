package com.cqcxi.flowEngine.controller.flow;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONObject;
import com.cqcxi.flowEngine.common.ActResult;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.enety.ActRuTask;
import com.cqcxi.flowEngine.image.ProcessImageService;
import com.cqcxi.flowEngine.model.TaskCompleteDto;
import com.cqcxi.flowEngine.model.TaskParam;
import com.cqcxi.flowEngine.model.TaskQueryVo;
import com.cqcxi.flowEngine.model.TaskStartDto;
import com.cqcxi.flowEngine.service.ActivitiService;
import com.cqcxi.flowEngine.service.IActRuTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述： 流程工作接口</p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 11:30  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@CrossOrigin
@Api(description = "流程基础功能")
@RestController
@RequestMapping("/act")
@Slf4j
public class ActivitiController {

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private IActRuTaskService iActRuTaskService;

    @Autowired
    private ProcessImageService processImageService;

    @ApiOperation(value = "启动流程", httpMethod = "POST", response = Object.class, notes = "启动流程")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/start")
    public HttpResp start(
            @RequestBody @Validated TaskStartDto startDto
    ){
        ActResult actResult = activitiService.startProcess(startDto);
        if (actResult.getResult()){
            //查询代办任务
            List<TaskQueryVo> actRuTasks = iActRuTaskService.selectByProcInstId((String) actResult.getData());
            HttpResp success = HttpResp.success();
            success.setData(actRuTasks);
            return success;
        }else {
            return HttpResp.error();
        }
    }

    @ApiOperation(value = "完成任务", httpMethod = "POST", response = Object.class, notes = "完成任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/approve")
    public HttpResp completeTask(
            @RequestBody @Validated TaskCompleteDto taskCompleteDto
            ){
        //查询流程procInstId
        ActRuTask task = iActRuTaskService.getById(taskCompleteDto.getTaskId());

        //任务参数
        List<TaskParam> params = taskCompleteDto.getTaskParams();
        Map<String, Object> paramMap = new HashMap<>();
        for (TaskParam param : params) {
            paramMap.put(param.getName(), param.getVal());
        }

        activitiService.completeTask(taskCompleteDto.getTaskId(), paramMap);

        //查询代办任务
        List<TaskQueryVo> actRuTasks = iActRuTaskService.selectByProcInstId(task.getProcInstId());
        return HttpResp.success(actRuTasks);
    }

    @ApiOperation(value = "查询任务", httpMethod = "POST", response = Object.class, notes = "查询任务")
    @ApiImplicitParams({
    })
    @RequestMapping("/task/query")
    public HttpResp taskQuery(
           @RequestBody  List<String> assignees
    ){
        ActResult actResult = activitiService.queryTask(assignees);
        return HttpResp.success(actResult.getData());
    }

    @ApiOperation(value = "停止流程", httpMethod = "POST", response = Object.class, notes = "停止流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", value="任务Id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/task/stop")
    public HttpResp stopTask(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ){
        activitiService.stopProcess(taskId);
        return HttpResp.success();
    }

    @ApiOperation(value = "获取表单数据", httpMethod = "POST", response = Object.class, notes = "获取表单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", value="任务Id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/form/query")
    public HttpResp form(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ){
        ActResult actResult = activitiService.fromData(taskId);
        return HttpResp.success(actResult.getData());
    }

    @ApiOperation(value = "获取发起人", httpMethod = "POST", response = Object.class, notes = "获取发起人")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", value="任务Id", dataType="String", paramType="query", required=false),
    })
    @RequestMapping("/initiator/query")
    public HttpResp starter(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ){
        ActResult actResult = activitiService.queryStarter(taskId);

        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("initiator",actResult.getData());

        return HttpResp.success(jsonObject);
    }



    @ApiOperation(value = "生成图片", httpMethod = "POST", response = String.class, notes = "image")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", value="taskId", dataType="String", paramType="query", required=true),
    })
    @RequestMapping("/image")
    public HttpResp image(
            @RequestParam(value = "taskId", defaultValue = "")String taskId
    ) {
        ActRuTask task = iActRuTaskService.getById(taskId);
        String id = task.getProcInstId();
        String base = null;
        try {
            InputStream image = processImageService.getFlowImgByProcInstId(id);
            base = Base64.encode(image);
            image.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpResp resp = HttpResp.success();

        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("base64",base);

        resp.setData(jsonObject);
        return resp;
    }
}
