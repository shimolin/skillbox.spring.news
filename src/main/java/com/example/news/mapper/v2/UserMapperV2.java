package com.example.news.mapper.v2;

import com.example.news.model.User;
import com.example.news.web.model.UserRequest;
import com.example.news.web.model.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapperV2 {

    UserResponse userToResponse(User user);

    User requestToUser(UserRequest request);

}
