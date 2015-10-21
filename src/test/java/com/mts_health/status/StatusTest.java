package com.mts_health.status;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StatusTest {

  private StatusController statusController;
  private StatusConfigurationHolder statusConfigurationHolder;

  @Before
  public void setup() {
    statusConfigurationHolder = mock(StatusConfigurationHolder.class);
    statusController = new StatusController(statusConfigurationHolder);
  }

  @Test
  public void
  should_return_status_configuration() throws IOException {
    statusController.status();
    verify(statusConfigurationHolder).getApplicationProperties();
  }
}