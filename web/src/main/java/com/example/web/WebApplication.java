package com.example.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableFeignClients
@EnableZuulProxy
public class WebApplication {

	private RestTemplate rest;
	private NameClient nameClient;

	public WebApplication(RestTemplate rest, NameClient nameClient) {
		this.rest = rest;
		this.nameClient = nameClient;
	}

	@RequestMapping
	public String ui() {
		String greeting = rest.getForObject("http://greeting", String.class);
		String name = nameClient.getName();
		return greeting + " " + name;
	}

	@FeignClient("name")
	interface NameClient {
		@RequestMapping(path = "/", method = RequestMethod.GET)
		String getName();
	}

	@Configuration
	protected static class Config {
		@Bean
		public Sampler sampler() {
		    return new AlwaysSampler();
		}

		@Bean
		@LoadBalanced
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
