package com.cqcxi.flowEngine.exception;

/**
 * <p>类描述：  </p>
 * <p>版权：ChongQingCXI Co.Itd All right reserved. 2021/10/25 13:51  </p>
 * <p>创建人员： 杨顺 </p>
 * <p>创建日期：2021/10/25 </p>
 * <p>开发公司：重庆创信智能科技有限公司 </p>
 */
import com.cqcxi.flowEngine.common.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yyss
 * @date 2018/6/19 17:16
 * @description 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpResp errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception{
        if (e.getClass() == MethodArgumentNotValidException.class){
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult result = me.getBindingResult();
            StringBuilder stringBuilder = new StringBuilder();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                if (errors != null) {
                    errors.forEach(p -> {
                        FieldError fieldError = (FieldError) p;
                        stringBuilder.append(fieldError.getDefaultMessage() + " ");
                    });
                }
            }
            return HttpResp.param(stringBuilder.toString());
        }
        e.printStackTrace();
        log.error(e.getMessage(),e);
        return HttpResp.error();
    }


}