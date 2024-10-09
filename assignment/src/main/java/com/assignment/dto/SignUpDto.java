package com.assignment.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpDto {

    private String password;
    private int id;
    private String name;
    @NotNull(message = "email should not be null")
    @Email(message = "Not a valid email")
    private String email;
    @NotNull(message = "phone number should not be null")
    @Digits(integer = 10, message = "Invalid phone number", fraction = 0)
    private String phoneNumber;
    private String address;
    @Digits(integer = 10, message = "Invalid pin code", fraction = 0)
    private String pinCode;
}

