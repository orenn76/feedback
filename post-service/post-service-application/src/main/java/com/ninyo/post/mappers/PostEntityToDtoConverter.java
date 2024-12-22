package com.ninyo.post.mappers;

import com.ninyo.crudcore.mappers.Converter;
import com.ninyo.post.model.Post;
import com.ninyo.post.model.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostEntityToDtoConverter implements Converter<Post, PostDto> {

    @Override
    public PostDto convert(Post source) {
        return PostDto.builder()
                .id(source.getId())
                .description(source.getDescription())
                .name(source.getName())
                .amount(source.getAmount())
                .build();
    }

}
