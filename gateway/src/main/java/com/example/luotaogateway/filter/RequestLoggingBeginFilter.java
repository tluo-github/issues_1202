package com.example.luotaogateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * request begin 打印耗时日志
 * User: luotao
 * Date: 2019/4/25
 * Time: 下午3:17
 */
@Component
public class RequestLoggingBeginFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());// 控制台日志打印

    public static final String START_TIME = "startTime";
    public static final String START_URL = "startURL";
    public static final String ROUTE_TIME = "reoutTime";
    public static final String ROUTE_URL = "roeuteURL";

    public RequestLoggingBeginFilter() {
        logger.info("Loaded GlobalFilter [RequestLoggingFilter]");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String begin = String.format("@@@@@@@@@@ begin: %s %s",
                exchange.getRequest().getMethod().name(),
                exchange.getRequest().getURI().toString()
        );

        logger.info(begin);

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        exchange.getAttributes().put(START_URL,exchange.getRequest().getURI().toString());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
