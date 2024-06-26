package com.rentstate.user_management.application;

import com.rentstate.user_management.domain.model.dto.request.LoginRequest;
import com.rentstate.user_management.domain.model.dto.request.RatingRequest;
import com.rentstate.user_management.domain.model.dto.request.UserCreateRequest;
import com.rentstate.user_management.domain.model.dto.request.UserUpdateRequest;
import com.rentstate.user_management.domain.model.dto.response.UserResponse;
import com.rentstate.user_management.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserCreateRequest createRequest){

        UserResponse userResponse = userService.create(createRequest);
        if (userResponse == null) return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId){

        UserResponse userResponse = userService.getById(userId);
        if(userResponse == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponse> updateUser
            (@RequestBody UserUpdateRequest updateRequest, @PathVariable Long userId){

        UserResponse userResponse = userService.update(updateRequest, userId);

        if(userResponse == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> deleteuser(@PathVariable Long userId){

        if(userService.delete(userId)){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/rating")
    public ResponseEntity<Boolean> addRating(@RequestBody RatingRequest request){
        if(userService.rateUser(request)){
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse>login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest);
        UserResponse userResponse = userService.login(loginRequest);
        if(userResponse == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userResponse );
    }


}
