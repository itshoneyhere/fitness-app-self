package com.example.userServer.mapper;

import com.example.userServer.dtos.RequestUser;
import com.example.userServer.dtos.ResponseUsers;
import com.example.userServer.model.Users;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static Users requestToEntity(RequestUser requestUser)
    {
        return Users.builder()
                .contact(requestUser.getContact())
                .name(requestUser.getName())
                .email(requestUser.getEmail())
                .build();

    }

    public static ResponseUsers entityToResponse(Users users)
    {
        return ResponseUsers.builder()
                .contact(users.getContact())
                .email(users.getEmail())
                .name(users.getName())
                .build();


    }
}
