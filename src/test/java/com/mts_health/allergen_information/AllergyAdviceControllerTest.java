package com.mts_health.allergen_information;

import com.mts_health.context_information.CountryHolder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllergyAdviceControllerTest {

  private AllergyAdviceController allergyAdviceController;
  private AllergenInformationRepository mockRepository;
  private AllergenAdvice mockAllergenAdvice;

  @Before
  public void
  setup() {
    mockAllergenAdvice = mock(AllergenAdvice.class);
    mockRepository = mock(AllergenInformationRepository.class);
    allergyAdviceController = new AllergyAdviceController(mockRepository);
  }

  @Test
  public void
  should_return_allergen_information_based_on_country_context() {
    AllergenAdvice irishAllergenAdvice = createAllergenAdvice();
    CountryHolder.INSTANCE.setCountry("IE");
    when(mockRepository.findOne("IE")).thenReturn(mockAllergenAdvice);
    assertThat(allergyAdviceController.getInformation().getBody(), is(irishAllergenAdvice.getCountryName()));
  }

  public AllergenAdvice createAllergenAdvice() {
    String countryISO = "IE";
    String countryName = "Ireland";
    List<String> listedHighRiskFoods = new ArrayList<>();
    listedHighRiskFoods.add("Peanuts");
    listedHighRiskFoods.add("Shellfish");
    String linkToNationalAllergenHealthWebsite = "http://ifan.ie/";

    return new AllergenAdvice(countryISO, countryName, listedHighRiskFoods, linkToNationalAllergenHealthWebsite);
  }
}