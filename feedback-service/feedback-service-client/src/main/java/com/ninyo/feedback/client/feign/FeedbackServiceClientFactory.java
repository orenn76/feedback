package com.ninyo.feedback.client.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninyo.common.client.feign.FeignClientFactory;
import com.ninyo.feedback.client.feign.exception.ClientErrorDecoder;
import feign.jackson.JacksonDecoder;

public class FeedbackServiceClientFactory {

    public FeedbackServiceClient createClient(String serviceUrl, ObjectMapper objectMapper) {
        return FeignClientFactory.createClient(FeedbackServiceClient.class, serviceUrl, objectMapper, new ClientErrorDecoder(new JacksonDecoder(objectMapper)));
    }
}
