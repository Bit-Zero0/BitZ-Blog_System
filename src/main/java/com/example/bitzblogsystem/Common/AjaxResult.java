package com.example.bitzblogsystem.Common;

import lombok.Data;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.io.Serializable;

/**
 * 统一格式返回
 */

@Data
public class AjaxResult implements Serializable {
    private Integer code;//状态码
    private String msg; // 状态码描述信息
    private Object data; //返回的数据


    // 成功时放回的结果
    public static AjaxResult success(Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static AjaxResult success(int code , Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg("");
        result.setData(data);
        return result;
    }

    public static AjaxResult success(int code , String msg , Object data){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    //失败时返回的结果
    public static AjaxResult fail(int code , String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static AjaxResult fail(int code, String msg, Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
