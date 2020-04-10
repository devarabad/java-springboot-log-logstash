package com.example.springboot.interceptor;

import com.example.springboot.common.LoggingService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * Logging requests (POST, PUT, PATCH, DELETE …)
 *  - For others methods that contains a body, we’ll use RequestBodyAdviceAdapter
 */
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter
{
  // Logging requests (POST, PUT, PATCH, DELETE …)
  private final LoggingService      loggingService;
  private final HttpServletRequest  httpServletRequest;

  public CustomRequestBodyAdviceAdapter(LoggingService loggingService, HttpServletRequest httpServletRequest)
  {
    this.loggingService     = loggingService;
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public boolean supports(MethodParameter methodParameter
                          , Type targetType
                          , Class<? extends HttpMessageConverter<?>> converterType)
  {
    return true;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage
                              , MethodParameter parameter
                              , Type targetType
                              , Class<? extends HttpMessageConverter<?>> converterType)
  {
    return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
  }
}
