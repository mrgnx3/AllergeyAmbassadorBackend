package com.mts_health.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "/status")
public class StatusController {

  private StatusConfigurationHolder statusConfigurationHolder;

  @Autowired
  public StatusController(StatusConfigurationHolder statusConfigurationHolder) {
    this.statusConfigurationHolder = statusConfigurationHolder;
  }

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public PackageJsonProperties status() throws IOException {
    return statusConfigurationHolder.getApplicationProperties();
  }
}
