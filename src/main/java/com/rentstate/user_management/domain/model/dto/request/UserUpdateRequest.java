package com.rentstate.user_management.domain.model.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String description;
    private String photoUrl;
    private Boolean isPremium;
}
