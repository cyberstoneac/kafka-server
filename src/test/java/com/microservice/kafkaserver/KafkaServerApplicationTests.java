package com.microservice.kafkaserver;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.microservice.kafkaserver.model.User;

import io.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KafkaServerApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class KafkaServerApplicationTests {

	@Before
	public void setUp() {
		RestAssured.port = 8081;
	}
	
	@Test
	public void testUserPublish() {
		
		User user = new User();
		user.setDept("IT");
		user.setName("Jet");
		user.setSalary(19L);
		
		expect()
		 	.statusCode(200)
			.body(containsString("Published user successfully"))
	 	.given()
			.body(user)
			.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
			.get("kafka/publish/json")
		;
	}

	@Test
	public void testMessagePublish() {
		
		expect()
		 	.statusCode(200)
			.body(containsString("Published message successfully"))
	 	.given()
	 		.header("message", "Hello from test case")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
		.when()
			.get("kafka/publish")
		;
		
	}

	
}
