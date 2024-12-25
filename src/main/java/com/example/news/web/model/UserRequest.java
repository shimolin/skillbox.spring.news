package com.example.news.web.model;

import com.example.news.model.Role;
import com.example.news.model.RoleType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Username (login) must be not blank")
    @Size(min = 3, max = 64, message = "3 <= FirstName.length <= 64 ")
    private String username;


    @NotBlank(message = "FirstName must be not blank")
    @Size(min = 3, max = 64, message = "3 <= FirstName.length <= 64 ")
    private String firstName;

    @NotBlank(message = "LastName must be not blank")
    @Size(min = 3, max = 64, message = "3 <= LastName.length <= 64 ")
    private String lastName;

    private LocalDate birthday;

    private String password;

//    private List<RoleType> roles;


}
