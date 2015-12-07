package com.mts.health.context_information;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class CountryHeaderListener implements ServletRequestListener {

  public static final String MTS_HEALTH_HEADER_COUNTRY = "country";

  @Override
  public void requestDestroyed(ServletRequestEvent requestEvent) {
    CountryHolder.INSTANCE.reset();
  }

  @Override
  public void requestInitialized(ServletRequestEvent requestEvent) {
    String country = ((HttpServletRequest) requestEvent.getServletRequest()).getHeader(MTS_HEALTH_HEADER_COUNTRY);
    if (StringUtils.isNotBlank(country)) {
      CountryHolder.INSTANCE.setCountry(country);
    }
  }

}
