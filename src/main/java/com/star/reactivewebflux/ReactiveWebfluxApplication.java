package com.star.reactivewebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@EnableWebFlux
@EnableReactiveFeignClients
@EnableFeignClients
@SpringBootApplication
public class ReactiveWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebfluxApplication.class, args);
    }

}
