package com.examplemicroservices.currency_exchange_service.Controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api-retry", fallbackMethod = "hardCodedResponse")
    @CircuitBreaker(name = "sample-api-circuit-breaker", fallbackMethod = "hardCodedResponse")
    public String sampleApi() {
        log.info("Sample api call received");
        ResponseEntity<String> entity =
                new RestTemplate().getForEntity("http://localhost:8080/some-dummy-api", String.class);
        return entity.getBody();
    }

    @GetMapping("/sample-api/circuit-breaker")
    @RateLimiter(name = "sample-api-rate-limiter", fallbackMethod = "hardCodedResponse")
    public String sampleApiCircuitBreaker() {
        log.info("Sample api call received for circuit breaker");
        return "sample api response for Circuit breaker";
    }

    private String hardCodedResponse(Exception exp){
        return "This is s fallback response";
    }
}
