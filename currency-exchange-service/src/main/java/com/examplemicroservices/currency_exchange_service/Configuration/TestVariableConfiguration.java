package com.examplemicroservices.currency_exchange_service.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limits-service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestVariableConfiguration {

    private String testVar;
}
