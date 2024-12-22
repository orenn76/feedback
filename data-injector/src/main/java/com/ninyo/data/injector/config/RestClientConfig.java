package com.ninyo.data.injector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ninyo.post.client.feign.PostServiceClient;
import com.ninyo.post.client.feign.PostServiceClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RestClientConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Bean
    public PostServiceClient postServiceClient(@Value("${postServiceClientUrl:http://localhost:8080/post-service}") String postServiceClientUrl) {
        log.info("postServiceClientUrl host: {}", postServiceClientUrl);
        return new PostServiceClientFactory().createClient(postServiceClientUrl, objectMapper());
    }

}
