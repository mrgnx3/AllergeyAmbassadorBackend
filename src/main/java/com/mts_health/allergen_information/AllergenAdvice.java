package com.mts_health.allergen_information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class AllergenAdvice {

  @Id
  @JsonProperty("countryISO")
  private String countryISO;

  @JsonProperty("countryName")
  private String countryName;

  @JsonProperty("listedHighRiskFoods")
  private List<String> listedHighRiskFoods;

  @JsonProperty("linkToNationalAllergenHealthWebsite")
  private String linkToNationalAllergenHealthWebsite;

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
