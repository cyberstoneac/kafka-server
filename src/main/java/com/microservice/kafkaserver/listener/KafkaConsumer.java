package com.microservice.kafkaserver.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservice.kafkaserver.model.User;

@Component
public class KafkaConsumer {

	@KafkaListener(topics = "kafka_topic", groupId = "group_id")
	public void consume(String message) {
		System.out.println("Consumed message: " + message);
	}

	@KafkaListener(topics = "kafka_topic_json", groupId = "group_id_json", containerFactory = "userKafkaListenerFactory")
	public void consumeJson(User user) {
		System.out.println("Consumed JSON Message: " + user);
	}

}
