package com.example.springboot.interceptor;

import com.example.springboot.common.LoggingService;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Logging responses
 */
@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object>
{
  private final LoggingService loggingService;

  public CustomResponseBodyAdviceAdapter(LoggingService loggingService)
  {
    this.loggingService = loggingService;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class converterType)
  {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body
                                , MethodParameter returnType
                                , MediaType selectedContentType
                                , Class selectedConverterType
                                , ServerHttpRequest request
                                , ServerHttpResponse response)
  {
    loggingService.logResponse( ((ServletServerHttpRequest) request).getServletRequest()
                                , ((ServletServerHttpResponse) response).getServletResponse()
                                , body);

    return body;
  }
}
