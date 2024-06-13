package com.example.news.mapper.v1;

import com.example.news.model.User;
import com.example.news.web.model.UserRequest;
import com.example.news.web.model.UserResponse;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {

    public UserResponse userToResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setBirthday(user.getBirthday());
        return response;
    }

    public User requestToUser(UserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthday(request.getBirthday());
        return user;
    }

}
