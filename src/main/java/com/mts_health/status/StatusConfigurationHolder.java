package com.mts_health.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class StatusConfigurationHolder {

  @Autowired
  private AbstractEnvironment environment;
  private static final String PACKAGE_JSON_FILE_PATH = "classpath:/package.json";
  protected String packageJsonFile;
  private PackageJsonProperties applicationProperties;

  public StatusConfigurationHolder() throws IOException {
    packageJsonFile = PACKAGE_JSON_FILE_PATH;
  }

  public PackageJsonProperties getApplicationProperties() throws IOException {
    applicationProperties = readPackageJson();
    return applicationProperties;
  }


  private PackageJsonProperties readPackageJson() throws IOException {
    return new ObjectMapper().readValue( new FileReader( new File("/Users/niallw/PersonalProjects/AllergyAmbassador/src/main/resources/package.json")),new TypeReference<PackageJsonProperties>() {
    });
  }
}
