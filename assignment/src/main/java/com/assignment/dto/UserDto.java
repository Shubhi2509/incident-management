package com.assignment.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * The type User dto.
 */
@Data
public class UserDto {
    private int id;
    private String name;
    @NotNull
    @Email(message = "Not a valid email")
    private String email;
    @NotNull
    @Digits(integer = 10, message = "Invalid phone number", fraction = 0)
    private String phoneNumber;
    private String address;
    @Digits(integer = 10, message = "Invalid pin code", fraction = 0)
    private String pinCode;
    private String city;
    private String country;
}
