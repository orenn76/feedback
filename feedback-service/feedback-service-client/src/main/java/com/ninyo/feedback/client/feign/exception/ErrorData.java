package com.ninyo.feedback.client.feign.exception;

import lombok.Data;

import java.util.List;

@Data
public class ErrorData {

    private List<Error> errors;

    @Data
    public static class Error {
        String code;
        String description;
    }

}
