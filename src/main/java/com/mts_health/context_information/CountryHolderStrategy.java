package com.mts_health.context_information;

import com.mts_health.utils.ThreadInformationHolderStrategy;
import org.springframework.core.NamedThreadLocal;

public class CountryHolderStrategy implements ThreadInformationHolderStrategy {
  private static final ThreadLocal<String> COUNTRY_HOLDER = new NamedThreadLocal<>("Country holder");

  @Override
  public String get() {
    return COUNTRY_HOLDER.get();
  }

  @Override
  public void set(String country) {
    COUNTRY_HOLDER.set(country);
  }

  @Override
  public void reset() {
    COUNTRY_HOLDER.remove();
  }
}

