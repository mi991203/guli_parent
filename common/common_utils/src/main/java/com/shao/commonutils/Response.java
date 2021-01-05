package com.shao.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Response {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    private Response(){}

    public static Response success(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResultCode.SUCCESS);
        response.setMessage("成功");
        return response;
    }

    public static Response error(){
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(ResultCode.SUCCESS);
        response.setMessage("成功");
        return response;
    }

    public Response success(Boolean b) {
        this.setSuccess(b);
        return this;
    }

    public Response code(Integer code){
        this.setCode(code);
        return this;
    }

    public Response message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public Response data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public Response data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
