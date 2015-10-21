package com.mts_health.allergen_information;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class AllergenAdvice {

  @Id
  private String countryISO;

  @NotNull
  private String countryName;

  private List<String> listedHighRiskFoods;

  private String linkToNationalAllergenHealthWebsite;

  public AllergenAdvice(String countryISO, String countryName, List<String> listedHighRiskFoods, String linkToNationalAllergenHealthWebsite) {
    this.countryISO = countryISO;
    this.countryName = countryName;
    this.listedHighRiskFoods = listedHighRiskFoods;
    this.linkToNationalAllergenHealthWebsite = linkToNationalAllergenHealthWebsite;
  }

  public String getCountryISO() {
    return countryISO;
  }

  public void setCountryISO(String countryISO) {
    this.countryISO = countryISO;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public List<String> getListedHighRiskFoods() {
    return listedHighRiskFoods;
  }

  public void setListedHighRiskFoods(List<String> listedHighRiskFoods) {
    this.listedHighRiskFoods = listedHighRiskFoods;
  }

  public String getLinkToNationalAllergenHealthWebsite() {
    return linkToNationalAllergenHealthWebsite;
  }

  public void setLinkToNationalAllergenHealthWebsite(String linkToNationalAllergenHealthWebsite) {
    this.linkToNationalAllergenHealthWebsite = linkToNationalAllergenHealthWebsite;
  }

}
