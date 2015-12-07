package com.mts.health.context_information;

import com.mts.health.utils.ThreadInformationHolderStrategy;

import java.util.Locale;

public enum CountryHolder {
  INSTANCE;

  private ThreadInformationHolderStrategy strategy = new CountryHolderStrategy();

  public String getCountry() {
    return strategy.get();
  }

  public Locale getLocale() {
    if (isCountrySet()) {
      return new Locale(getCountry());
    }
    return null;
  }

  public boolean isCountrySet() {
    return getCountry() != null;
  }

  public void setCountry(String country) {
    strategy.set(country);
  }

  public void setStrategy(CountryHolderStrategy strategy) {
    this.strategy = strategy;
  }

  public void reset() {
    strategy.reset();
  }
}

