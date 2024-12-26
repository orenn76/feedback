package com.ninyo.feedback.model;

import com.ninyo.common.crudcore.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "#{@feedbackConfig.getIndexName()}")
public class Feedback implements BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Field(type = FieldType.Date, name = "createdAt")
    private Instant createdAt;

    @Field(type = FieldType.Date, name = "updatedAt")
    private Instant updatedAt;

    @Field(type = FieldType.Object, name = "user")
    //@NotNull TODO oren
    private User user;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Integer, name = "votes")
    private int votes; // Used as NPS (NPS = % promoters - % detractors) but without detractors

    @Enumerated(EnumType.STRING)
    private Status status;

}