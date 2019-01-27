package com.juliodias.zuulapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZuulLoggingFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        LOGGER.info("Request: {}, Request URI: {}", request, request.getRequestURI());
        return null;
    }
}
