package com.ninyo.feedback.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@ComponentScan(basePackages = {"com.ninyo.feedback", "com.ninyo.common"})
public class FeedbackConfig {

    @Value("${feedback.index.name}")
    private String indexName;

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return builder;
    }

    public String getIndexName() {
        return indexName;
    }
}
