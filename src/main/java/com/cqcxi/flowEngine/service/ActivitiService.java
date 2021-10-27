package com.cqcxi.flowEngine.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RuntimeUtil;
import com.cqcxi.flowEngine.common.ActResult;
import com.cqcxi.flowEngine.enety.ActRuTask;
import com.cqcxi.flowEngine.mapper.ActRuTaskMapper;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FormProperty;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

/**
 * <p>类描述： 工作流功能实现</p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 11:31  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Service
@Slf4j
public class ActivitiService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActRuTaskMapper actRuTaskMapper;

    /**
     * 方法描述：启动流程
     * 创建人员：杨顺
     * 创建时间： 2021/10/25 11:33
     * 修改人员：
     * 修改内容：
     * 修改时间：
     * @param processId 流程ID
     */
    public ActResult startProcess (String processId){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processId);
        return ActResult.success(processInstance.getProcessInstanceId());
    }

    /**
     * 方法描述：完成任务
     * 创建人员：杨顺
     * 创建时间： 2021/10/25 14:14
     * 修改人员：
     * 修改内容：
     * 修改时间：
     * @param taskId 任务Id
     * @param param
     */
    public ActResult completeTask (String taskId, Map<String, Object> param){
        //没有参数
        if (param == null || param.size() == 0){
            taskService.complete(taskId );
        }
        //有参数
        else {
            taskService.complete(taskId, param);
        }
        return ActResult.success();
    }

    /**
     * 方法描述：查询任务
     * 创建人员：杨顺
     * 创建时间： 2021/10/25 15:27
     * 修改人员：
     * 修改内容：
     * 修改时间：
     * @param assignees 代理人Id
     */
    public ActResult queryTask(List<String> assignees){
        List<HashMap<String,Object>> maps = new ArrayList<>();
        List<Task> tasks = taskService.createTaskQuery().taskAssigneeIds(assignees).list();
        for (Task task : tasks) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("task_Id",task.getId());
            map.put("task_Name",task.getName());
            map.put("assignee",task.getAssignee());
            map.put("createTime", DateUtil.dateNew(task.getCreateTime()).toString());
            maps.add(map);
        }
        return ActResult.success(maps);
    }

    /**
     * 方法描述：停止流程
     * 创建人员：杨顺
     * 创建时间： 2021/10/26 17:05
     * 修改人员：
     * 修改内容：
     * 修改时间：
     * @param taskId 任务Id
     */
    public ActResult stopProcess(String taskId){
        //查询任务ProcInstId
        ActRuTask actRuTask = actRuTaskMapper.selectById(taskId);
        String procInstId = actRuTask.getProcInstId();
        Map<String, Object> params = new HashMap<>();
        params.put("PROC_INST_ID_", procInstId);
        actRuTaskMapper.deleteByMap(params);
        return ActResult.success();
    }

    /**
     * 方法描述：获取节点表单数据
     * 创建人员：杨顺
     * 创建时间： 2021/10/27 10:12
     * 修改人员：
     * 修改内容：
     * 修改时间：
     * @param taskId
     */
    public ActResult fromData(String taskId){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取task对应的表单内容 需要TaskDefinitionKey
        UserTask userTask = (UserTask) repositoryService.getBpmnModel(task.getProcessDefinitionId())
                .getFlowElement(task.getTaskDefinitionKey());
        List<FormProperty> formProperties = userTask.getFormProperties();
        return ActResult.success(formProperties);
    }


    /**
     * 生成图片
     */
//    public InputStream getProcessDiagram(String processInstanceId) {
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(processInstanceId).singleResult();
//
//        // null check
//        if (processInstance != null) {
//            // get process model
//            BpmnModel model = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
//
//            if (model != null && model.getLocationMap().size() > 0) {
//                ProcessDiagramGenerator generator = new DefaultProcessDiagramGenerator();
//                // 生成流程图 已启动的task 高亮
//                return generator.generateDiagram(model,
//                        runtimeService.getActiveActivityIds(processInstanceId));
//                // 生成流程图 都不高亮
////                return generator.generateDiagram(model, "宋体","宋体","宋体");
//            }
//        }
//        return null;
//    }


}
