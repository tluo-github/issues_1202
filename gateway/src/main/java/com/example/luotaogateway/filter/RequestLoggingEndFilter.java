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
 * @Description request end 打印耗时日志
 * @Author luotao
 * @Date 2019/9/19 下午6:24
 */
@Component
public class RequestLoggingEndFilter implements GlobalFilter, Ordered {

    private Logger logger = LoggerFactory.getLogger(this.getClass());// 控制台日志打印


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            final Long startTime = exchange.getAttribute(RequestLoggingBeginFilter.START_TIME);
            final String startUrl = exchange.getAttribute(RequestLoggingBeginFilter.START_URL);
            final Long routerTime = exchange.getAttribute(RequestLoggingBeginFilter.ROUTE_TIME);
            final String routeUrl = exchange.getAttribute(RequestLoggingBeginFilter.ROUTE_URL);

            if (startTime != null && routeUrl != null) {
                final Long endTime = (System.currentTimeMillis() - startTime);
                String end = String.format("@@@@@@@@@@ end: %s %s, route-url: %s route-time: %s, end time: %s",
                        exchange.getRequest().getMethod().name(),
                        startUrl,
                        routeUrl,
                        routerTime + " ms",
                        endTime + " ms"
                );
                logger.info(end);
            }

        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
