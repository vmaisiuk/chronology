package com.andersen.chronology.logging.configs.configs;

import ch.qos.logback.access.tomcat.LogbackValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessLogConfiguration {

  @Bean
  public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    LogbackValve logbackValve = new LogbackValve();
    logbackValve.setFilename(String.format("conf/%s", LogbackValve.DEFAULT_FILENAME));
    factory.addContextValves(logbackValve);
    return factory;
  }
}
