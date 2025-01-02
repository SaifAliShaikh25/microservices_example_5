package com.examplemicroservices.currency_exchange_service.Controller;

import com.examplemicroservices.currency_exchange_service.Entity.CurrencyExchange;
import com.examplemicroservices.currency_exchange_service.Service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService service;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
        /*return CurrencyExchange.builder().id(100L).from(from).to(to).conversionMultiple(BigDecimal.valueOf(50))
                .environment(environment.getProperty("local.server.port")).build();*/
                CurrencyExchange currencyExchange =  service.getDataUsingFromAndTo(from, to);
                if(Objects.isNull(currencyExchange))
                    throw new RuntimeException("Unable to find record with from " + from + " and to " + to);
                else
                    return currencyExchange.toBuilder().environment(environment.getProperty("local.server.port")).build();
    }

    @GetMapping("/all-data")
    public List<CurrencyExchange> retriveAllData() {
        return service.getAllData();
    }
}
