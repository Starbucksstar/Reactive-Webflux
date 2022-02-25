package com.star.reactivewebflux.controller;

import com.star.reactivewebflux.ReactiveTestBase;
import com.star.reactivewebflux.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserControllerTest extends ReactiveTestBase {

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void should_get_user_info_when_user_id_exist_success() {
        when(userService.getUserInfo(anyString())).thenReturn(Mono.just("xiaoming"));
        String responseBody = testClient.get()
                                        .uri(uriBuilder -> uriBuilder.path("/api/v1/user/1")
                                                                     .queryParam("origin", "macos")
                                                                     .build())
                                        .header("user", "user-name")
                                        .exchange()
                                        .expectStatus()
                                        .isOk()
                                        .expectBody(String.class)
                                        .returnResult()
                                        .getResponseBody();
        assertThat(responseBody).isEqualTo("xiaoming");
    }


    @Test
    void should_create_company_success() {
        when(userService.verify()).thenReturn(Mono.just("success"));
        String responseBody = testClient.post().uri(
                uriBuilder ->
                        uriBuilder.path("/api/v1/user/verify")
                                  .build())
                                        .header("user", "user-name")
                                        .bodyValue("star")
                                        .exchange()
                                        .expectStatus()
                                        .isOk()
                                        .expectBody(String.class)
                                        .returnResult()
                                        .getResponseBody();
        assertThat(responseBody).isEqualTo("success");
    }


}

