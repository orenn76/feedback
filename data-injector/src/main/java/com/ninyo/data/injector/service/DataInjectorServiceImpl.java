package com.ninyo.data.injector.service;

import com.ninyo.post.client.feign.PostServiceClient;
import com.ninyo.post.model.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInjectorServiceImpl implements DataInjectorService {

    @Autowired
    PostServiceClient postServiceClient;

    public void create(PostDto postDto) {
        postServiceClient.create(postDto);
    }
}
