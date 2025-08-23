package com.examplemicroservices.currency_conversion_service.Service;

import com.examplemicroservices.currency_conversion_service.Configuration.RestTemplateConfiguration;
import com.examplemicroservices.currency_conversion_service.Entity.CurrencyConversion;
import com.examplemicroservices.currency_conversion_service.FeignClient.CurrencyExchangeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class CurrencyConversionService {

    @Autowired
    private CurrencyExchangeFeignClient client;

    @Autowired
    private RestTemplate restTemplate;

    public CurrencyConversion calculateCurrencyConversion(String from, String to, Integer quantity) {
//        ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity(
        ResponseEntity<CurrencyConversion> response = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class,
                new HashMap<String, String>() {{
                    put("from", from);
                    put("to", to);
                }}
        );

        CurrencyConversion currencyConversion = response.getBody();
        return currencyConversion.toBuilder()
                .id(currencyConversion.getId())
                .from(currencyConversion.getFrom())
                .to(currencyConversion.getTo())
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)))
                .environment(currencyConversion.getEnvironment() + " from Rest Template")
                .build();
    }

    public CurrencyConversion calculateCurrencyConversionUsingFeignClient(String from, String to,
                                                                          Integer quantity) {
        CurrencyConversion currencyConversion = client.retrieveCurrencyExchangeValue(from, to);

        return currencyConversion.toBuilder()
                .id(currencyConversion.getId())
                .from(currencyConversion.getFrom())
                .to(currencyConversion.getTo())
                .quantity(quantity)
                .conversionMultiple(currencyConversion.getConversionMultiple())
                .totalCalculatedAmount(currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)))
                .environment(currencyConversion.getEnvironment() + " from Feign Client response")
                .build();
    }
}
