package com.shao.servicebase.exceptionhandler;

import com.shao.commonutils.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response errorException(Exception ex) {
        ex.printStackTrace();
        return Response.error().message("执行了全局异常返回");
    }

    @ResponseBody
    @ExceptionHandler(GuliException.class)
    public Response errorException(GuliException ex) {
        ex.printStackTrace();
        return Response.error().code(ex.getCode()).message(ex.getMsg());
    }
}
