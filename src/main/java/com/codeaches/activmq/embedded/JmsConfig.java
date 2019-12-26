package com.codeaches.activmq.embedded;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

  Logger log = LoggerFactory.getLogger(JmsConfig.class);

  @Value("${activemq.broker-url}")
  String brokerUrl;

  @Bean
  BrokerService broker() throws Exception {

    BrokerService broker = new BrokerService();
    broker.setPersistent(false);
    broker.setUseJmx(true);
    broker.addConnector(brokerUrl);
    return broker;
  }

  @Bean
  JmsTemplate jmsTemplate() {
    return new JmsTemplate(new PooledConnectionFactory(brokerUrl));
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(new PooledConnectionFactory(brokerUrl));
    return factory;
  }
}
