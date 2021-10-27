package com.cqcxi.flowEngine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqcxi.flowEngine.enety.ActRuTask;

import java.util.List;

/**
 * <p>类描述： 任务表 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 16:55  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/7/13 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
public interface IActRuTaskService extends IService<ActRuTask> {
    //根据流程部署ID查询
    List<ActRuTask> selectByProcInstId (String id);


}
