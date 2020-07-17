package com.example.api.common;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

import org.slf4j.Logger;

public class AppLogger
{
  private final Logger logger;

  public AppLogger(Class<?> clazz)
  {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public void info(String msg)
  {
    this.info(msg, null, null, LogType.STRING);
  }

  public void info(String msg, String logCategory)
  {
    this.info(msg, logCategory, null, LogType.STRING);
  }

  public void info(String msg, String logCategory, Map<String, String> fields)
  {
    this.info(msg, logCategory, fields, LogType.STRING);
  }

  public void info(Object msg, LogType logType)
  {
    this.info(msg, null, null, logType);
  }

  public void info(Object msg, String logCategory, LogType logType)
  {
    this.info(msg, logCategory, null, logType);
  }

  public void info(Object msg, String logCategory, Map<String, String> fields, LogType logType)
  {
    if (logCategory != null)
    {
      MDC.put("log_category", logCategory);
    }

    if (fields != null && !fields.isEmpty())
    {
      fields.forEach(MDC::put);
    }

    switch (logType)
    {
      case STRING:
        logger.info(msg.toString());
        break;
      case JSON_STRING:
        logger.info(JsonUtil.toJson(msg));
        break;
    }

    if (logCategory != null)
    {
      MDC.remove("log_category");
    }

    if (fields != null && !fields.isEmpty())
    {
      fields.forEach((k,v) -> MDC.remove(k));
    }
  }
}
