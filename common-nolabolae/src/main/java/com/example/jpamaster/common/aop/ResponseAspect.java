package com.example.jpamaster.common.aop;

import com.example.jpamaster.common.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ResponseAspect implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper;
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponse) {
            return body;
        } else if (body instanceof String) {

            String ret = null;

            try {
                ret = objectMapper.writeValueAsString(ApiResponse.createOk(body));
            } catch (JsonProcessingException e) {
                log.error("json parsing error : {}", e.getMessage(), e);
            }

            return ret;
        }

        return ApiResponse.createOk(body);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


}