package com.mts_health.allergen_information;

import com.mts_health.context_information.CountryHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AllergyAdviceController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AllergyAdviceController.class);
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

  @RequestMapping(value = "/allergen_advice/form", method = RequestMethod.PUT)
  public ResponseEntity<String> save(@RequestBody AllergenAdvice request) {
    AllergenAdvice savedAllergenAdvice = repository.save(request);

    LOGGER.info("Allergen Advice saved: " + savedAllergenAdvice);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}