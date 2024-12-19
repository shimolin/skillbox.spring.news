package com.example.news.web.model;

import com.example.news.model.Role;
import com.example.news.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    private Set<RoleType> roles;

}
