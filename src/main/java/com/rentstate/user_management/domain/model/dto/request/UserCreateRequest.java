package com.rentstate.user_management.domain.model.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
}
