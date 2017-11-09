package com.example.greeting;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class GreetingApplication {

	private final Log log = LogFactory.getLog(getClass());

	@Value("${greeting}")
	private String greeting;

	@RequestMapping
	public String greeting() {
		log.info("Greeting: "+greeting);
		return greeting;
	}

	@Bean
	public Sampler sampler() {
	    return new AlwaysSampler();
	}

	public static void main(String[] args) {
		SpringApplication.run(GreetingApplication.class, args);
	}
}
