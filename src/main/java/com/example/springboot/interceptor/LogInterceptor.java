package com.example.springboot.interceptor;

import com.example.springboot.common.LoggingService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Logging requests (GET methods)
 *  - The GET methods don’t contain body so we’ll use a HandlerInterceptor for this case.
 */
@Component
public class LogInterceptor implements HandlerInterceptor
{
  private final LoggingService loggingService;

  public LogInterceptor(LoggingService loggingService)
  {
    this.loggingService = loggingService;
  }

  @Override
  public boolean preHandle( HttpServletRequest request
                            , HttpServletResponse response
                            , Object handler)
    throws Exception
  {
    // For inbound requests - Generate a unique correlationId
    String correlationId  = request.getHeader("X-Correlation-ID");

    if (correlationId == null)
    {
      correlationId = UUID.randomUUID().toString();
    }

    request.setAttribute("correlationId", correlationId);

    // Log requests for http [GET]
    if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
        && request.getMethod().equals(HttpMethod.GET.name()))
    {
      loggingService.logRequest(request, null, correlationId);
    }

    // Log requests for http [DELETE]
    if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
      && request.getMethod().equals(HttpMethod.DELETE.name()))
    {
      loggingService.logRequest(request, null, correlationId);
    }

    return true;
  }

  @Override
  public void postHandle( HttpServletRequest request
                          , HttpServletResponse response
                          , Object handler
                          , ModelAndView modelAndView)
    throws Exception
  {

  }

  @Override
  public void afterCompletion(HttpServletRequest request
                              , HttpServletResponse response
                              , Object handler
                              , Exception ex)
    throws Exception
  {

  }
}
