package com.ninyo.feedback.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder(toBuilder = true)
@Data
@JsonDeserialize(builder = FeedbackDto.FeedbackDtoBuilder.class)
public class FeedbackDto {

    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private UserDto user;
    private String type;
    private String title;
    private String description;
    private int votes;
    private String status;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class FeedbackDtoBuilder {
    }

}