package com.mts.health.allergen_information;

import com.mts.health.context_information.CountryHolder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContextInformationTests {

  @Test
  public void
  should_return_country_header_currently_saved_in_context() {
    String currentlySetCountryHolder = "IE";
    CountryHolder.INSTANCE.setCountry(currentlySetCountryHolder);
    assertThat(currentlySetCountryHolder, is(CountryHolder.INSTANCE.getCountry()));
  }
}
