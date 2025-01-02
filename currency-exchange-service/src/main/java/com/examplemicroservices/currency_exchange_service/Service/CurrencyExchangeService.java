package com.examplemicroservices.currency_exchange_service.Service;

import com.examplemicroservices.currency_exchange_service.Entity.CurrencyExchange;
import com.examplemicroservices.currency_exchange_service.Repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository repository;

    public List<CurrencyExchange> getAllData() {
        return repository.findAll();
    }

    public CurrencyExchange getDataUsingFromAndTo(String from, String to) {
        return repository.findByFromAndTo(from, to);
    }
}
