package com.example.springboot.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoggingService
{
  void logRequest(HttpServletRequest httpServletRequest, Object body, String correlationId);
  void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
