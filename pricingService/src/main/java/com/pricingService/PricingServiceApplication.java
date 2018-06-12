package com.pricingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring initializer
 * 
 * @author yeshendrav
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.pricingService.repository")
@EnableHystrixDashboard
@EnableCircuitBreaker
public class PricingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingServiceApplication.class, args);
	}
}
