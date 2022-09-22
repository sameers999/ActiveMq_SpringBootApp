package com.imi.reciver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {

	private String mqReceverMessage;

	public String getMqReceverMessage() {
		return mqReceverMessage;
	}

	//@JmsListener(destination = "demo.queue")
	public void consumeMessage(String message) {

		mqReceverMessage = message;
		System.out.println("Received message: " + mqReceverMessage);
	}
}
