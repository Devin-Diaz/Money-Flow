package com.diazdevin.financetracker.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userRole;

}
