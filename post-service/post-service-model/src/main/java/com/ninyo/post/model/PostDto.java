package com.ninyo.post.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder(toBuilder = true)
@Data
@JsonDeserialize(builder = PostDto.PostDtoBuilder.class)
public class PostDto {

    private String id;
    private String name;
    private String description;
    private Integer amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PostDtoBuilder {
    }

}