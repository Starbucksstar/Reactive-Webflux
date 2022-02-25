package com.star.reactivewebflux.controller;

import com.star.reactivewebflux.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/verify")
    public Mono<String> verify() {
        return userService.verify()
                          .log("verify success")
                          .delayElement(Duration.ofMillis(1000L));
    }

    @GetMapping("/{id}")
    public Mono<String> getUserInfo(@PathVariable("id") String id) {
        return userService.getUserInfo(id)
                          .log("get user info success");
    }
}
