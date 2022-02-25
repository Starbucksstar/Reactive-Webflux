package com.star.reactivewebflux.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.naming.ServiceUnavailableException;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class ClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String method, Response response) {

        try {
            String responseBody =
                    nonNull(response.body()) ? IOUtils.toString(response.body().asReader()) : "";

            log.error(
                    "feign client get error response: request: {} {} response status: {} response body: {}",
                    response.request().method(),
                    response.request().url(),
                    response.status(),
                    responseBody);

            return new RuntimeException(responseBody);
        } catch (Exception e) {
            log.error("feign client response decode error, {}", e.getMessage(), e);
            return new ServiceUnavailableException(response.toString());
        }
    }
}
