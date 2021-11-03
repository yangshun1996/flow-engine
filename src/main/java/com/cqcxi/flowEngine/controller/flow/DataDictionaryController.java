package com.cqcxi.flowEngine.controller.flow;

import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.service.DataDictionaryService;
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
 * <p>类描述： 数据字典 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/11/3 14:28  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/11/3 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Api(description = "数据字典")
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("act/dictionary")
public class DataDictionaryController {
    @Autowired
    private DataDictionaryService dataDictionaryService;

    @ApiOperation(value = "添加字典", httpMethod = "POST", response = Object.class, notes = "添加字典")
    @ApiImplicitParams({
    })
    @RequestMapping("/create")
    public HttpResp createDictionary(

    ){
        return HttpResp.success();
    }
}
