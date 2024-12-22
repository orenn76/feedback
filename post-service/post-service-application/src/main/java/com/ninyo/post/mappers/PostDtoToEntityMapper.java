package com.ninyo.post.mappers;

import com.ninyo.crudcore.mappers.Mapper;
import com.ninyo.post.model.PostDto;
import com.ninyo.post.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostDtoToEntityMapper implements Mapper<PostDto, Post> {

    public void map(PostDto dto, Post post) {
        post.setName(dto.getName());
        post.setDescription(dto.getDescription());
        post.setAmount(dto.getAmount());
    }
}
