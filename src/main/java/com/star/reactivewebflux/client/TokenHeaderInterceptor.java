package com.star.reactivewebflux.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reactivefeign.client.ReactiveHttpRequest;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Slf4j
public class TokenHeaderInterceptor implements ReactiveHttpRequestInterceptor {

    @Override
    public Mono<ReactiveHttpRequest> apply(ReactiveHttpRequest reactiveHttpRequest) {
        return Mono.just(reactiveHttpRequest)
                .flatMap(request ->
                        Mono.deferContextual(ctx -> {
                            Map<String, List<String>> headers = request.headers();
                            setHeaderIfNonBlank("request_id", ctx, headers);
                            return Mono.just(request);
                        })
                );
    }

    private void setHeaderIfNonBlank(String key, ContextView ctx, Map<String, List<String>> headers) {
        String requestId = ctx.getOrDefault(key,"");
        if (StringUtils.isNotBlank(requestId)) {
            headers.put(key, Arrays.asList(requestId));
        }
    }
}
