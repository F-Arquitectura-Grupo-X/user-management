package com.rentstate.user_management.domain.model.dto.request;

import lombok.Data;

@Data
public class RatingRequest {

    private Long userId;
    private Long ratedUserId;
    private int rating;
}
