package com.example.luotaogateway;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @Description TODO
 * @Author luotao
 * @Date 2020/7/13 下午6:24
 */
public class MyExceptionHandler  implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        System.out.println("luotao...");
        return Mono.error(throwable);
    }
}
