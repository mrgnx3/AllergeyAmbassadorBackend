package com.mts_health.allergen_information;

import com.mts_health.context_information.CountryHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AllergyAdviceController {

  private AllergenInformationRepository repository;

  @Autowired
  public AllergyAdviceController(AllergenInformationRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(value = "/allergen_advice", method = RequestMethod.GET)
  public ResponseEntity<AllergenAdvice> getInformation() {
    AllergenAdvice allergenAdvice = repository.findOne(CountryHolder.INSTANCE.getCountry());
    return new ResponseEntity<>(allergenAdvice, HttpStatus.OK);
  }
}
