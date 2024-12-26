package com.ninyo.feedback.mappers;

import com.ninyo.common.crudcore.mappers.Mapper;
import com.ninyo.feedback.model.*;
import org.springframework.stereotype.Component;

@Component
public class FeedbackDtoToEntityMapper implements Mapper<FeedbackDto, Feedback> {

    public void map(FeedbackDto dto, Feedback feedback) {
        feedback.setCreatedAt(dto.getCreatedAt());
        feedback.setUpdatedAt(dto.getUpdatedAt());
        if (dto.getUser() != null) {
            feedback.setUser(
                    User.builder()
                            .name(dto.getUser().getName())
                            .email(dto.getUser().getEmail())
                            .build());
        }
        feedback.setType(Type.fromValue(dto.getType()));
        feedback.setTitle(dto.getTitle());
        feedback.setDescription(dto.getDescription());
        feedback.setVotes(dto.getVotes());
        feedback.setStatus(Status.fromValue(dto.getStatus()));
    }
}
