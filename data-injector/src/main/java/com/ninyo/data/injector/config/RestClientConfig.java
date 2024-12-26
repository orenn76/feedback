package com.ninyo.data.injector.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ninyo.feedback.client.feign.FeedbackServiceClient;
import com.ninyo.feedback.client.feign.FeedbackServiceClientFactory;
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
    public FeedbackServiceClient feedbackServiceClient(@Value("${feedbackServiceClientUrl:http://localhost:8000/feedback-service}") String feedbackServiceClientUrl) {
        log.info("feedbackServiceClientUrl host: {}", feedbackServiceClientUrl);
        return new FeedbackServiceClientFactory().createClient(feedbackServiceClientUrl, objectMapper());
    }

}
