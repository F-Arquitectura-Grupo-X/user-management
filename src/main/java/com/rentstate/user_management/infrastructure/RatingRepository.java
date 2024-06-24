package com.rentstate.user_management.infrastructure;

import com.rentstate.user_management.domain.model.entities.Rating;
import com.rentstate.user_management.domain.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findByUserAndRatedUser(User user, User ratedUser);

}
