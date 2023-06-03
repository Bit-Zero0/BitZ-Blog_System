package com.example.bitzblogsystem.Config;

import com.example.bitzblogsystem.Common.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 实现统一数据返回的保底类
 * 说明：在返回数据之前，检测数据的类型是否为统一的对象，如果不是，封装成统一的对象
 */
public class ResponseAdvice implements ResponseBodyAdvice {
    @Autowired
    private ObjectMapper objectMapper;

    //调用beforeBodyWrite的开关，如果是true才会调用beforeBodyWrite
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    //对数据格式进行效验和封装
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof AjaxResult) return body;

        if(body instanceof String){
            return objectMapper.writeValueAsString(AjaxResult.success(body));
        }
        return AjaxResult.success(body);
    }
}
