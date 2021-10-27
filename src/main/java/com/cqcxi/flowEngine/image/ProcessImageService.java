package com.cqcxi.flowEngine.image;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Base64;

/**
 * <p>类描述：  </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/9/2 17:38  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/7/13 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Service
public class ProcessImageService {
    @Autowired

    ProcessImageManager processImageManager;


    /**
     * 根据流程实例Id获取流程图
     *
     * @param procInstId 流程实例id
     * @return inputStream
     * @throws Exception exception
     */
    public InputStream getFlowImgByProcInstId(String procInstId) throws Exception {
        InputStream resourceAsStream  = processImageManager.getFlowImgByProcInstId(procInstId);
        return resourceAsStream;
    }
}
