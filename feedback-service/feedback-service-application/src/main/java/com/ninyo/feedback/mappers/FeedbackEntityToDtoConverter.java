package com.ninyo.feedback.mappers;

import com.ninyo.common.crudcore.mappers.Converter;
import com.ninyo.feedback.model.Feedback;
import com.ninyo.feedback.model.FeedbackDto;
import com.ninyo.feedback.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class FeedbackEntityToDtoConverter implements Converter<Feedback, FeedbackDto> {

    @Override
    public FeedbackDto convert(Feedback source) {
        FeedbackDto feedbackDto = FeedbackDto.builder()
                .id(source.getId())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .type(source.getType().toString())
                .title(source.getTitle())
                .description(source.getDescription())
                .votes(source.getVotes())
                .build();
        if (source.getStatus() != null) {
            feedbackDto.setStatus(source.getStatus().toString());
        }
        if (source.getUser() != null) {
            feedbackDto.setUser(UserDto.builder()
                    .name(source.getUser().getName())
                    .email(source.getUser().getEmail())
                    .build());
        }
        return feedbackDto;
    }

}
