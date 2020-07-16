package com.example.springboot.common;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

public class AppLogger
{
  private final Logger logger;

  public AppLogger(Class<?> clazz)
  {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public void info(String msg, String logCategory)
  {
    this.info(msg, logCategory, new LinkedHashMap<String, String>());
  }

  public void info(String msg, String logCategory, List<LogField> fields)
  {
    Map<String, String> fieldMap = new LinkedHashMap<String, String>();
    fields.forEach(logField -> fieldMap.put(logField.getKey(), logField.getValue()));
    this.info(msg, logCategory, fieldMap);
  }

  public void info(String msg, String logCategory, Map<String, String> fields)
  {
    if (logCategory != null)
    {
      MDC.put("log_category", logCategory);
    }

    if (fields != null && !fields.isEmpty())
    {
      fields.forEach(MDC::put);
    }

    logger.info(msg);

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
