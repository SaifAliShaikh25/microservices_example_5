package com.examplemicroservices.limitsmicroservice.controller;

import com.examplemicroservices.limitsmicroservice.configuration.Configuration;
import com.examplemicroservices.limitsmicroservice.entity.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("limits")
    public Limits getAllLimits(){
        // return Limits.builder().minimum(1).maximum(100).build();
        return Limits.builder().minimum(configuration.getMinimum()).maximum(configuration.getMaximum()).build();
    }

}
