package com.mts_health.context_information;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CountryHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

  public static final String FORWARDING_COUNTRY_HEADER = "country";

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    if (CountryHolder.INSTANCE.isCountrySet()) {
      request.getHeaders().add(FORWARDING_COUNTRY_HEADER, CountryHolder.INSTANCE.getCountry());
    }
    return execution.execute(request, body);
  }

}
