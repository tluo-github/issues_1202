package com.example.luotaogateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * 记录route 地址和耗时
 * User: luotao
 * Date: 2019/4/25
 * Time: 下午2:18
 */
@Component
public class RequestRouteLoggingFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());// 控制台日志打印

    public RequestRouteLoggingFilter() {
        logger.info("Loaded GlobalFilter [RequestRouteLoggingFilter]");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        Long beginTime = exchange.getAttribute(RequestLoggingBeginFilter.START_TIME);
        Long routeTime = (System.currentTimeMillis() - beginTime);
        exchange.getAttributes().put(RequestLoggingBeginFilter.ROUTE_TIME, routeTime);
        exchange.getAttributes().put(RequestLoggingBeginFilter.ROUTE_URL, exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR).toString());
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 10200;
    }
}
