package com.star.reactivewebflux.service;

import com.star.reactivewebflux.client.WechatFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private WechatFeignClient wechatFeignClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_verify_user_success() {
        when(wechatFeignClient.verify()).thenReturn(Mono.just("success"));
        Mono<String> results = userService.verify();

        StepVerifier.create(results)
                    .consumeNextWith(result -> {
                        assertThat(result).isEqualTo("success");
                    })
                    .verifyComplete();
    }

    @Test
    void should_get_user_info_success_when_user_id_exist() {
        when(wechatFeignClient.verify()).thenReturn(Mono.just("success"));
        when(wechatFeignClient.findUserInfo(anyString())).thenReturn(Mono.just("xiaoming"));
        Mono<String> results = userService.getUserInfo("1");

        StepVerifier.create(results)
                    .consumeNextWith(result -> {
                        assertThat(result).isEqualTo("successxiaoming");
                    })
                    .verifyComplete();
    }
}
