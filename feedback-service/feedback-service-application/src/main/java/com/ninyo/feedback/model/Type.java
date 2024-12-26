package com.ninyo.feedback.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Type {

    OTHER("Other"),
    SUGGESTION("Suggestion"),
    FEATURE_REQUEST("Feature Request"),
    BUG("Bug");

    private final String value;

    Type(final String value) {
        this.value = value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static Type fromValue(final String value) {
        return Stream.of(Type.values())
                .filter(enumValue  -> enumValue .value.equalsIgnoreCase(value))
                .findAny()
                .orElse(OTHER);
    }

    @Override
    public String toString() {
        return this.value;
    }

}