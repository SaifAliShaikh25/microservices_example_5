package com.examplemicroservices.currency_conversion_service.FeignClient;

import com.examplemicroservices.currency_conversion_service.Entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
@FeignClient(name = "currency-exchange") //url not used when we are using client side load balancing using feign client
public interface CurrencyExchangeFeignClient {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveCurrencyExchangeValue(@PathVariable("from") String from,
                                                            @PathVariable("to") String to);
}
