package com.examplemicroservices.currency_conversion_service.Controller;

import com.examplemicroservices.currency_conversion_service.Entity.CurrencyConversion;
import com.examplemicroservices.currency_conversion_service.Service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-conversion/")
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService service;

    @GetMapping("from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String from, @PathVariable("to") String to,
                                                                     @PathVariable("quantity") Integer quantity) {

        return service.calculateCurrencyConversion(from, to, quantity);
    }

    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionUsingFeignClientResponse(@PathVariable("from") String from, @PathVariable("to") String to,
                                                          @PathVariable("quantity") Integer quantity) {

        return service.calculateCurrencyConversionUsingFeignClient(from, to, quantity);
    }
}
