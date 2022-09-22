package com.imi.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class AMQConfig {
	
	@Value("${activemq.broker.url}")
	String mq_url;

    // for injuctting the mq Object / storing object
    @Bean
    Queue queue() {
        return new ActiveMQQueue("demo.queue");
    }

    // for collecting  ActiveMQ object
    @Bean
    ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(mq_url);
        return factory;
    }

    // for posting the messages
    @Bean
    JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }
	
	
}
