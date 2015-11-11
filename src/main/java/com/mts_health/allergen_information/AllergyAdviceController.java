package com.mts_health.allergen_information;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mts_health.context_information.CountryHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
@RequestMapping(value = "/allergen_advice")
public class AllergyAdviceController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AllergyAdviceController.class);
  private AllergenInformationRepository repository;

  @Autowired
  public AllergyAdviceController(AllergenInformationRepository repository) {
    this.repository = repository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<AllergenAdvice> getInformation() {
    AllergenAdvice allergenAdvice = repository.findOne(CountryHolder.INSTANCE.getCountry());
    LOGGER.info("AllergenAdvice returned for: " + CountryHolder.INSTANCE.getCountry());
    return new ResponseEntity<>(allergenAdvice, HttpStatus.OK);
  }

  @RequestMapping(value = "/form", method = RequestMethod.GET)
  public ModelAndView getForm(@Param(value = "country") final String country) throws JsonProcessingException {
    return new ModelAndView("/create-allergen-national-information-form", new HashMap<String, AllergenAdvice>(){{
      put("country", repository.findOne(country));
    }});
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<String> delete(@Param(value = "id") String id) {
    repository.delete(id);
    LOGGER.info("Allergy Advice was deleted for ISO " + id);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<String> save(@RequestBody AllergenAdvice request) {
    AllergenAdvice savedAllergenAdvice = repository.save(request);

    LOGGER.info("Allergen Advice saved: " + savedAllergenAdvice);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

}