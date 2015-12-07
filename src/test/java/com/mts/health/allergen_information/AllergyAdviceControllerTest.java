package com.mts.health.allergen_information;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.mts.health.context_information.CountryHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AllergyAdviceControllerTest {

  private AllergyAdviceController allergyAdviceController;
  private AllergenInformationRepository mockRepository;
  private AllergenAdvice allergenAdvice;

  private static Logger LOGGER;

  @Mock
  private Appender<ILoggingEvent> mockLogAppender;

  @Captor
  private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

  @Before
  public void
  setup() {
    LOGGER = (Logger) LoggerFactory.getLogger(AllergyAdviceController.class);
    LOGGER.setLevel(Level.INFO);
    LOGGER.addAppender(mockLogAppender);

    allergenAdvice = mock(AllergenAdvice.class);
    mockRepository = mock(AllergenInformationRepository.class);
    allergyAdviceController = new AllergyAdviceController(mockRepository);
  }

  @Test
  public void
  should_return_allergen_information_based_on_country_context() {
    when(mockRepository.findOne("IE")).thenReturn(allergenAdvice);
    CountryHolder.INSTANCE.setCountry("IE");
    allergyAdviceController.getInformation();

    assertThat(allergyAdviceController.getInformation().getBody(), is(allergenAdvice));
  }

  @Test
  public void
  should_correctly_log_results_for_country_context() {
    when(mockRepository.findOne("IE")).thenReturn(allergenAdvice);
    CountryHolder.INSTANCE.setCountry("IE");
    allergyAdviceController.getInformation();

    verify(mockLogAppender, times(1)).doAppend(captorLoggingEvent.capture());
    assertThat(captorLoggingEvent.getAllValues().get(0).getMessage(), is("AllergenAdvice returned for: IE"));
  }
}