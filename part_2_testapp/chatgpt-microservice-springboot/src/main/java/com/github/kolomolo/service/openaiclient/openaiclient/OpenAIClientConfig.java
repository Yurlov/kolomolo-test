package com.github.kolomolo.service.openaiclient.openaiclient;

import feign.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;

@Configuration
@Indexed
@Data
@Slf4j
@EnableFeignClients
public class OpenAIClientConfig {

    @Value("${openai-service.http-client.read-timeout}")
    private int readTimeout;

    @Value("${openai-service.http-client.connect-timeout}")
    private int connectTimeout;

    @Value("${openai-service.api-key}")
    private String apiKey;

    @Value("${openai-service.gpt-model}")
    private String model;

    @Value("${openai-service.audio-model}")
    private String audioModel;

    @Bean
    public Request.Options options() {
        return new Request.Options(getConnectTimeout(), getReadTimeout());
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return request -> request.header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);
    }
}
