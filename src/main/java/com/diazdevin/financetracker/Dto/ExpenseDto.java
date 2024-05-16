package com.diazdevin.financetracker.Dto;

import com.diazdevin.financetracker.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDto {

    private String category;
    private String payee;
    private String description;
    private BigDecimal amount;
    private String date;
    private Long userId;

}
