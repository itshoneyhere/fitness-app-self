package com.example.userServer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseUsers {


    public String name;


    public String email;


    public String contact;
}
