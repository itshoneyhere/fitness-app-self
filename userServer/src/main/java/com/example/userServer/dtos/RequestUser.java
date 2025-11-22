package com.example.userServer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestUser {

    @NotNull(message = "Name can't be null")
    public String name;

    @Email(message = "Invalid email")
    public String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid phone number. Must be a 10-digit Indian number starting with 6, 7, 8, or 9"
    )
    public String contact;
}
