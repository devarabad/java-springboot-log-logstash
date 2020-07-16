package com.example.springboot.common;

public class LogField
{
  private final String key;
  private final String value;

  public LogField(String key, String value)
  {
    this.key    = key;
    this.value  = value;
  }

  public String getKey()
  {
    return key;
  }

  public String getValue()
  {
    return value;
  }
}
