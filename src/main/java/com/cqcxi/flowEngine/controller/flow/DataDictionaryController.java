package com.cqcxi.flowEngine.controller.flow;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqcxi.flowEngine.common.HttpResp;
import com.cqcxi.flowEngine.entity.DataDictionary;
import com.cqcxi.flowEngine.model.DataDictionaryCreateDto;
import com.cqcxi.flowEngine.service.DataDictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @RequestBody @Validated DataDictionaryCreateDto dataDictionaryCreateDto
            ){

        QueryWrapper<DataDictionary> wrapper = new QueryWrapper<DataDictionary>();
        wrapper.eq("name",dataDictionaryCreateDto.getName());
        DataDictionary one = dataDictionaryService.getOne(wrapper);
        if (one != null){
            return HttpResp.error("参数名已经存在");
        }

        //存库
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setPageName(dataDictionaryCreateDto.getPageName());
        dataDictionary.setName(dataDictionaryCreateDto.getName());
        dataDictionary.setValue(dataDictionaryCreateDto.getValue());
        dataDictionary.setType(dataDictionaryCreateDto.getType());
        dataDictionaryService.save(dataDictionary);

        return HttpResp.success();
    }

    @ApiOperation(value = "查询字典", httpMethod = "POST", response = Object.class, notes = "查询字典")
    @ApiImplicitParams({
    })
    @RequestMapping("/list")
    public HttpResp queryDictionary(
    ){
        List<DataDictionary> list = dataDictionaryService.list();
        return HttpResp.success(list);
    }

    @ApiOperation(value = "修改字典", httpMethod = "POST", response = Object.class, notes = "修改字典")
    @ApiImplicitParams({
    })
    @RequestMapping("/update")
    public HttpResp updateDictionary(
            @RequestBody DataDictionary dataDictionary
    ){
        if (dataDictionary.getId() == null){
            return HttpResp.param("请传入Id");
        }
        dataDictionaryService.updateById(dataDictionary);
        return HttpResp.success();
    }
}
