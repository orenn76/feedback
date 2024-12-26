package com.ninyo.feedback.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
@JsonDeserialize(builder = UserDto.UserDtoBuilder.class)
public class UserDto {

    private String name;
    private String email;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class UserDtoBuilder {
    }

}