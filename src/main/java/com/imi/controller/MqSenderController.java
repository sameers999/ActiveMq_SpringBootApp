package com.imi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imi.service.MqService;

@RestController
@RequestMapping("/message")
public class MqSenderController {

	@Autowired
	private MqService mqService;

	@GetMapping("/send")
	public String sendMassageToMq() {
		
		System.out.println("message come to the  controller ..");

		return mqService.sendMassageToMq();
	}

	@GetMapping(value ="/recive",produces = MediaType.APPLICATION_JSON_VALUE)
	public String reciveMessageFromMq() {

		return mqService.reciveMessageFromMq();
	}

}
