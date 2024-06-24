package com.rentstate.user_management.domain.model.dto.response;

import com.rentstate.user_management.domain.model.entities.Rating;
import com.rentstate.user_management.domain.model.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String description;
    private Boolean isPremium;
    private String photoUrl;
    private Double rating;

    public UserResponse( User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.description = user.getDescription();
        this.isPremium = user.getIsPremium();
        this.photoUrl = user.getPhotoUrl();
        this.rating = getAverageRating(user.getReceivedRatings());
    }



    private Double getAverageRating(List<Rating> ratings){
        if(ratings.isEmpty()) return 5.0;

        return ratings.stream().mapToInt(Rating::getRating)
                        .average().orElse(5.0);
    }

}
