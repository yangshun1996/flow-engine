package com.cqcxi.flowEngine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqcxi.flowEngine.enety.ModelStatus;
import com.cqcxi.flowEngine.mapper.ModelStatusMapper;
import com.cqcxi.flowEngine.service.ModelStatusService;
import org.springframework.stereotype.Service;

/**
 * <p>类描述： 流程表 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/26 17:29  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/26 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Service
public class ModelStatusServiceImpl extends ServiceImpl<ModelStatusMapper, ModelStatus> implements ModelStatusService {
}
