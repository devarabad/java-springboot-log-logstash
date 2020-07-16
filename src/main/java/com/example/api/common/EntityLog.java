package com.example.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityLog
{
  private final String              environment;
  private final String              service;
  private final String              correlationId;
  private final String              method;
  private final String              path;
  private final String              logCategory;
  private final Map<String, String> headers;
  private final Object              payload;

  public EntityLog( String environment
                    , String service
                    , String correlationId
                    , String method
                    , String path
                    , String logCategory
                    , Map<String, String> headers
                    , Object payload)
  {
    this.environment    = environment;
    this.service        = service;
    this.correlationId  = correlationId;
    this.method         = method;
    this.path           = path;
    this.logCategory    = logCategory;
    this.headers        = headers;
    this.payload        = payload;
  }

  public String getEnvironment()
  {
    return environment;
  }

  public String getService()
  {
    return service;
  }

  public String getCorrelationId()
  {
    return correlationId;
  }

  public String getMethod()
  {
    return method;
  }

  public String getPath()
  {
    return path;
  }

  public String getLogCategory()
  {
    return logCategory;
  }

  public Map<String, String> getHeaders()
  {
    return headers;
  }

  public Object getPayload()
  {
    return payload;
  }
}
