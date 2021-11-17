package com.cqcxi.flowEngine.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>类描述： 数据字典 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/11/3 14:35  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/11/3 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class DataDictionaryCreateDto {

    @NotBlank(message = "请传入页面显示名字")
    private String pageName;

    @NotBlank(message = "请传入字段名")
    private String name;

    @NotBlank(message = "请传入参数值")
    private String value;

    @NotNull(message = "请传入字典类型")
    private Integer type;
}
