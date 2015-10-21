package com.mts_health.allergen_information;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenInformationRepository extends MongoRepository<AllergenAdvice, String> {

}
