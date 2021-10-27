package com.cqcxi.flowEngine.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>类描述： 工作流类返回体 </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 11:35  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
@Data
public class ActResult {
    private Boolean result ; // 0 失败   1 成功

    private Object data;//携带参数

    public ActResult(boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

    //成功
    public static ActResult success(){
        return new ActResult(true , null);
    }

    public static ActResult success(Object obj){
        return new ActResult(true , obj);
    }

    //失败
    public static ActResult err(){
        return new ActResult(false , null);
    }
}
