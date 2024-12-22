package com.ninyo.post.client.feign;

import com.ninyo.crudcore.responses.IdResponse;
import com.ninyo.post.model.PostDto;
import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface PostServiceClient {

    @RequestLine("POST /")
    IdResponse<String> create(PostDto postDto);
}
