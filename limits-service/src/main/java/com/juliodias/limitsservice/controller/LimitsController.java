package com.juliodias.limitsservice.controller;

import com.juliodias.limitsservice.bean.Configuration;
import com.juliodias.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("limits")
    public LimitConfiguration retrieveLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("fault-tolerance")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveConfiguration")
    public LimitConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available!");
    }

    public LimitConfiguration fallbackRetrieveConfiguration() {
        return new LimitConfiguration(9, 999);
    }
}
