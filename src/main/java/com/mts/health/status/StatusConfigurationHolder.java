package com.mts.health.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

@Configuration
public class StatusConfigurationHolder {

  @Autowired
  private static final String PACKAGE_JSON_FILE_PATH = "classpath:package.json";
  protected String packageJsonFile;
  private Map<String, Object> applicationProps;

  @Autowired
  private AbstractEnvironment env;

  public StatusConfigurationHolder() throws IOException {
    packageJsonFile = PACKAGE_JSON_FILE_PATH;
  }

  @PostConstruct
  public void loadApplicationProperties() throws IOException {
    applicationProps = readPackageJson();

    Iterator<PropertySource<?>> iterator = env.getPropertySources().iterator();

    while (iterator.hasNext()) {
      Object propertySource = iterator.next();
      if (propertySource instanceof ResourcePropertySource) {
        applicationProps.putAll(((ResourcePropertySource) propertySource).getSource());
      }
    }
  }

  public Map<String, Object> getApplicationProperties() {
    return applicationProps;
  }


  private Map<String, Object> readPackageJson() throws IOException {
    InputStream inputFile = new PathMatchingResourcePatternResolver().getResource(PACKAGE_JSON_FILE_PATH).getInputStream();
    return new ObjectMapper().readValue(inputFile, new TypeReference<Map<String, String>>() {
    });
  }
}
