package com.rentstate.user_management.domain.service;

import com.rentstate.user_management.domain.model.dto.request.LoginRequest;
import com.rentstate.user_management.domain.model.dto.request.RatingRequest;
import com.rentstate.user_management.domain.model.dto.request.UserCreateRequest;
import com.rentstate.user_management.domain.model.dto.request.UserUpdateRequest;
import com.rentstate.user_management.domain.model.dto.response.UserResponse;

public interface UserService {

    UserResponse create(UserCreateRequest createRequest);
    UserResponse getById(Long userId);
    UserResponse update(UserUpdateRequest updateRequest,Long userId);
    Boolean delete (Long userId);

    Boolean rateUser(RatingRequest ratingRequest);

    UserResponse login(LoginRequest loginRequest);

}
