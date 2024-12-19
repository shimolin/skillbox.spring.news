package com.example.news.mapper.v2;

import com.example.news.model.Role;
import com.example.news.model.RoleType;
import com.example.news.model.User;
import com.example.news.web.model.UserRequest;
import com.example.news.web.model.UserResponse;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapperV2{

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "roles", source = "roles", qualifiedByName = "getUserRoles")
    public abstract UserResponse userToResponse(User user);

    @Mapping(target = "password",  source= "password", qualifiedByName = "encodePassword")
    public abstract User requestToUser(UserRequest request);

    @Named("encodePassword")
    protected String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    @Named("getUserRoles")
    protected Set<RoleType> getUserRoles(List<Role> roles){
        return roles.stream().map(Role::getAuthority).collect(Collectors.toSet());
    }

}
