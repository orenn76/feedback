package com.ninyo.post.client.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninyo.client.feign.FeignClientFactory;
import com.ninyo.post.client.feign.exception.ClientErrorDecoder;
import feign.jackson.JacksonDecoder;

public class PostServiceClientFactory {

    public PostServiceClient createClient(String serviceUrl, ObjectMapper objectMapper) {
        return FeignClientFactory.createClient(PostServiceClient.class, serviceUrl, objectMapper, new ClientErrorDecoder(new JacksonDecoder(objectMapper)));
    }
}
