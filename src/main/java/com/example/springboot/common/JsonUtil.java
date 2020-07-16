package com.example.springboot.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtil
{
  public static String toJson(Object object)
  {
    String jsonStr = "";

    try
    {
      ObjectMapper mapper = new ObjectMapper();
      jsonStr             = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
    catch (JsonProcessingException e)
    {
      // Cannot convert object to json string
    }

    return jsonStr;
  }
}