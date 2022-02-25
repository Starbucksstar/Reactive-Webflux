package com.star.reactivewebflux.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "${feign.thirdparty:wechat}",
        configuration = {ClientErrorDecoder.class, TokenHeaderInterceptor.class})
public interface WechatFeignClient {

    @PostMapping("/wechat/verify")
    Mono<String> verify();

    @GetMapping("/wechat/user")
    Mono<String> findUserInfo(@RequestParam("id") String userId);
}
