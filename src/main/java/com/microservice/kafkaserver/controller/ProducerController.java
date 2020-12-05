package com.microservice.kafkaserver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.kafkaserver.model.User;

@RestController
@RequestMapping("kafka")
public class ProducerController {

	@Value("${kafka.topic.string}")
	private String topicString;

	@Value("${kafka.topic.json}")
	private String topicJson;

	@Qualifier("kafkaTemplateForUser")
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplateForUser;

	@Qualifier("kafkaTemplate")
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@GetMapping("/publish/json")
	public String postUser(@RequestBody User user) {
		kafkaTemplateForUser.send(topicJson, user);
		return "Published user successfully";
	}

	@GetMapping("/publish")
	public String postString(HttpServletRequest request) {
		kafkaTemplate.send(topicString, request.getHeader("message"));
		return "Published message successfully";
	}

}
