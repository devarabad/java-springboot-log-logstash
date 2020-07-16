package com.example.api.resource.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityResponse
{
  private final String  status;
  private final String  message;
  private final Object  entity;

  public EntityResponse(String status
                        , String message
                        , Object entity)
  {
    this.status   = status;
    this.message  = message;
    this.entity   = entity;
  }

  public String getStatus()
  {
    return status;
  }

  public String getMessage()
  {
    return message;
  }

  public Object getEntity()
  {
    return entity;
  }
}
