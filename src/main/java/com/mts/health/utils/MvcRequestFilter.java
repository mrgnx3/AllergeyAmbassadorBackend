package com.mts.health.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MvcRequestFilter implements Filter {

  private final Logger logger = LoggerFactory.getLogger(MvcRequestFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request instanceof HttpServletRequest &&  response instanceof HttpServletResponse) {

      logger.info("Incoming request : {}{}",  ((HttpServletRequest) request).getRequestURL(), (((HttpServletRequest) request).getQueryString() != null ? ((HttpServletRequest) request).getQueryString(): ""));

      if (logger.isDebugEnabled() && ((HttpServletRequest) request).getMethod().equals("POST") && "application/json".equals(((HttpServletRequest) request).getHeader("Content-Type"))) {
        String jsonBody = IOUtils.toString(request.getInputStream());
        logger.debug(jsonBody);
      }

      chain.doFilter(request, response);
    }
  }

  @Override
  public void destroy() {
  }
}
