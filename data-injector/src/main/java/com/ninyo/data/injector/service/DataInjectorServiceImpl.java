package com.ninyo.data.injector.service;

import com.ninyo.feedback.client.feign.FeedbackServiceClient;
import com.ninyo.feedback.model.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataInjectorServiceImpl implements DataInjectorService {

    @Autowired
    FeedbackServiceClient feedbackServiceClient;

    public void create(FeedbackDto feedbackDto) {
        feedbackServiceClient.create(feedbackDto);
    }
}
