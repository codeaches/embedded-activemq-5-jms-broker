package com.codeaches.activmq.embedded;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsEmbeddedBroker {

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
}
