package com.rentstate.user_management.domain.service.impl;

import com.rentstate.user_management.domain.model.dto.request.LoginRequest;
import com.rentstate.user_management.domain.model.dto.request.RatingRequest;
import com.rentstate.user_management.domain.model.dto.request.UserCreateRequest;
import com.rentstate.user_management.domain.model.dto.request.UserUpdateRequest;
import com.rentstate.user_management.domain.model.dto.response.UserResponse;
import com.rentstate.user_management.domain.model.entities.Rating;
import com.rentstate.user_management.domain.model.entities.User;
import com.rentstate.user_management.domain.service.UserService;
import com.rentstate.user_management.infrastructure.RatingRepository;
import com.rentstate.user_management.infrastructure.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    @Override
    public UserResponse create(UserCreateRequest createRequest) {

        if(userRepository.existsByUsername(createRequest.getEmail()))
            return null;

        User newUser = new User(createRequest);
        userRepository.save(newUser);
        return new UserResponse(newUser);
    }

    @Override
    public UserResponse getById(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        return user.map(UserResponse::new).orElse(null);

    }

    @Override
    public UserResponse update(UserUpdateRequest updateRequest, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return null;

        User userUpdate = user.get();
        userUpdate.updateUser(updateRequest);
        userRepository.save(userUpdate);

        return new UserResponse(userUpdate);
    }

    @Override
    public Boolean delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return false;

        userRepository.delete(user.get());
        return true;
    }

    @Override
    public Boolean rateUser(RatingRequest ratingRequest) {

        Optional<User> user = userRepository.findById(ratingRequest.getUserId());
        if(user.isEmpty()) return false;

        Optional<User> ratedUser = userRepository.findById(ratingRequest.getRatedUserId());
        if(ratedUser.isEmpty()) return false;

        Rating existingRating = ratingRepository
                .findByUserAndRatedUser (user.get(), ratedUser.get());

        if (existingRating != null) {
            existingRating.setRating(ratingRequest.getRating());
            ratingRepository.save(existingRating);
        } else {
            User rateUser = ratedUser.get();

            Rating newRating = Rating.builder()
                    .user(user.get())
                    .ratedUser(rateUser)
                    .rating(ratingRequest.getRating())
                    .build();
            ratingRepository.save(newRating);

            rateUser.getReceivedRatings().add(newRating);
            userRepository.save(rateUser);

        }
        return true;
    }

    @Override
    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getEmail());
        if(user.getPassword().equals(loginRequest.getPassword())){
            return new UserResponse(user);
        }else{
            return null;
        }
    }

}
