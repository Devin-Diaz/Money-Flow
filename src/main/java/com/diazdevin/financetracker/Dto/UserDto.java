package com.diazdevin.financetracker.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private BigDecimal budget = BigDecimal.valueOf(0.00); // Set default value
    private String userRole;

}
