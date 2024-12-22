package com.ninyo.post.model;

import com.ninyo.crudcore.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "postindex")
public class Post implements BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Integer, name = "amount")
    private Integer amount;

}