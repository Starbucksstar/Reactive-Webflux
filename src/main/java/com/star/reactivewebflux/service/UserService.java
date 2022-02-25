package com.star.reactivewebflux.service;

import com.star.reactivewebflux.client.WechatFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final WechatFeignClient wechatFeignClient;

    public Mono<String> verify() {
        return wechatFeignClient.verify();
    }

    public Mono<String> getUserInfo(String userId) {
        Mono<String> verifyMono = wechatFeignClient.verify();
        Mono<String> userInfoMono = wechatFeignClient.findUserInfo(userId);
        return Mono.zip(verifyMono, userInfoMono).flatMap(result -> {
            String verify = result.getT1();
            String userInfo = result.getT2();
            System.out.println(verify+userInfo);
            return Mono.just(verify + userInfo);
        });
    }

}
