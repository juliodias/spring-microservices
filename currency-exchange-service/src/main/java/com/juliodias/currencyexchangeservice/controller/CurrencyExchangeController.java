package com.juliodias.currencyexchangeservice.controller;

import com.juliodias.currencyexchangeservice.bean.ExchangeValue;
import com.juliodias.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private Environment environment;
    private ExchangeValueRepository exchangeValueRepository;

    public CurrencyExchangeController(Environment environment, ExchangeValueRepository exchangeValueRepository) {
        this.environment = environment;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));

        LOGGER.info("Exchange value: {}", exchangeValue);
        return exchangeValue;
    }
}
