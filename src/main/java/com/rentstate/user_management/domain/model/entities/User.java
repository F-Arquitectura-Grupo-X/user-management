package com.rentstate.user_management.domain.model.entities;

import com.rentstate.user_management.domain.model.dto.request.UserCreateRequest;
import com.rentstate.user_management.domain.model.dto.request.UserUpdateRequest;
import com.rentstate.user_management.domain.model.valueobjects.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    private Integer age;
    private String gender;
    private String description;

    @NotNull
    private Boolean isPremium;

    private String photoUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "ratedUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> receivedRatings;

    public User(UserCreateRequest createRequest) {
        this.name = createRequest.getName();
        this.lastName = createRequest.getLastName();
        this.email = createRequest.getEmail();
        this.username = createRequest.getEmail();
        this.password = createRequest.getPassword();
        this.receivedRatings = new ArrayList<>();

        age = 18;
        gender = "Reserved";
        description = "";
        isPremium = false;
        photoUrl = "https://www.webiconio.com/_upload/255/image_255.svg";
        role = Role.USER;
    }

    public void updateUser (UserUpdateRequest updateRequest) {
        this.name = updateRequest.getName();
        this.lastName = updateRequest.getLastName();
        this.username = updateRequest.getEmail();
        this.email = updateRequest.getEmail();
        this.age = updateRequest.getAge();
        this.gender = updateRequest.getGender();
        this.description = updateRequest.getDescription();
        this.photoUrl = updateRequest.getPhotoUrl();
        this.isPremium = updateRequest.getIsPremium();
    }

}
