package com.juliodias.currencyconversionservice.controller;

import com.juliodias.currencyconversionservice.CurrencyExchangeServiceProxy;
import com.juliodias.currencyconversionservice.bean.CurrencyConversionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionController.class);

    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    public CurrencyConversionController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    @GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        LOGGER.info("Convert from {} - to {} - quantity {}", from, to, quantity);
        CurrencyConversionBean bean = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        LOGGER.info("Response: {}", bean);
        return new CurrencyConversionBean(bean.getId(), from, to, bean.getConversionMultiple(), quantity, quantity.multiply(bean.getConversionMultiple()), bean.getPort());
    }
}
