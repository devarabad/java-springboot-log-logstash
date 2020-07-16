package com.example.api.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingServiceImpl implements LoggingService
{
  Logger log = LoggerFactory.getLogger(this.getClass());

  @Value("${environment}")
  private String environment;

  @Value("${app.name}")
  private String applicationName;

  @Override
  public void logRequest(HttpServletRequest httpServletRequest, Object body, String correlationId)
  {
    /**
    StringBuilder stringBuilder     = new StringBuilder();
    Map<String, String> parameters  = buildParametersMap(httpServletRequest);

    stringBuilder.append("REQUEST ");
    stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
    stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
    stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");

    if (!parameters.isEmpty())
    {
      stringBuilder.append("parameters=[").append(parameters).append("] ");
    }

    if (body != null)
    {
      stringBuilder.append("body=[" + body + "]");
    }

    log.info(stringBuilder.toString());
    //**/

    String environment          = this.environment;
    String service              = this.applicationName;
    String method               = httpServletRequest.getMethod();
    String path                 = httpServletRequest.getRequestURI();
    String logCategory          = "start";
    Map<String, String> headers = buildHeadersMap(httpServletRequest);
    Object payload              = body;

    EntityLog entityLog = new EntityLog(environment, service, correlationId, method, path, logCategory, headers, payload);
    ObjectMapper mapper = new ObjectMapper();

    try
    {
      MDC.put("correlation_id", correlationId);
      MDC.put("method", method);
      MDC.put("path", path);
      MDC.put("log_category", logCategory);
      String jsonLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entityLog);
      log.info(jsonLog);
      MDC.remove("log_category");
      /**
      MDC.remove("method");
      MDC.remove("path");
      //*/
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body)
  {
    /**
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("RESPONSE ");
    stringBuilder.append("correlationId=[").append(httpServletRequest.getAttribute("correlationId")).append("] ");
    stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
    stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
    stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
    stringBuilder.append("responseBody=[").append(body).append("] ");

    log.info(stringBuilder.toString());
    //*/

    String environment          = this.environment;
    String service              = this.applicationName;
    String correlationId        = httpServletRequest.getAttribute("correlationId").toString();
    String method               = httpServletRequest.getMethod();
    String path                 = httpServletRequest.getRequestURI();
    String logCategory          = "end";
    Map<String, String> headers = buildHeadersMap(httpServletRequest);
    Object payload              = body;

    EntityLog entityLog = new EntityLog(environment, service, correlationId, method, path, logCategory, headers, payload);
    ObjectMapper mapper = new ObjectMapper();

    try
    {
      /**
      MDC.put("method", method);
      MDC.put("path", path);
      //*/
      MDC.put("log_category", logCategory);
      String jsonLog = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entityLog);
      log.info(jsonLog);
      MDC.remove("log_category");
      MDC.remove("correlation_id");
      MDC.remove("method");
      MDC.remove("path");
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
  }

  private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest)
  {
    Map<String, String> resultMap       = new HashMap<>();
    Enumeration<String> parameterNames  = httpServletRequest.getParameterNames();

    while (parameterNames.hasMoreElements())
    {
      String key    = parameterNames.nextElement();
      String value  = httpServletRequest.getParameter(key);
      resultMap.put(key, value);
    }

    return resultMap;
  }

  private Map<String, String> buildHeadersMap(HttpServletRequest request)
  {
    Map<String, String> map = new HashMap<>();

    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements())
    {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }

    return map;
  }

  private Map<String, String> buildHeadersMap(HttpServletResponse response)
  {
    Map<String, String> map = new HashMap<>();

    Collection<String> headerNames = response.getHeaderNames();
    for (String header : headerNames)
    {
      map.put(header, response.getHeader(header));
    }

    return map;
  }
}
