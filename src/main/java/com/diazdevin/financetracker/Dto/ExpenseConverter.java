package com.diazdevin.financetracker.Dto;

import com.diazdevin.financetracker.Model.Expense;
import com.diazdevin.financetracker.Model.User;

public class ExpenseConverter {
    public static Expense convertDtoToEntity(ExpenseDto expenseDto, User user) {
        return new Expense(
                expenseDto.getCategory(),
                expenseDto.getPayee(),
                expenseDto.getDescription(),
                expenseDto.getAmount(),
                expenseDto.getDate(),
                user
        );
    }
}
