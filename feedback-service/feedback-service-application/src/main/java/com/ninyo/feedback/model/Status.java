package com.ninyo.feedback.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Status {

    OPEN("Open"),
    UNDER_REVIEW("Under review"),
    PLANNED("Planned"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    DECLINED("Declined");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static Status fromValue(final String value) {
        return Stream.of(Status.values())
                .filter(enumValue -> enumValue.value.equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return this.value;
    }

}