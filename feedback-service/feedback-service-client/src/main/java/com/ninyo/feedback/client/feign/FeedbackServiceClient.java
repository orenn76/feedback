package com.ninyo.feedback.client.feign;

import com.ninyo.common.crudcore.responses.IdResponse;
import com.ninyo.feedback.model.FeedbackDto;
import feign.Headers;
import feign.RequestLine;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface FeedbackServiceClient {

    @RequestLine("POST /")
    IdResponse<String> create(FeedbackDto feedbackDto);
}
