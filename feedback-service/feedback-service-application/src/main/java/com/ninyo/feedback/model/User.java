package com.ninyo.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //@NotBlank TODO oren
    private String name;

    //@NotBlank TODO oren
    //@Email TODO oren
    private String email;

}