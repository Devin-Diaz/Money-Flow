package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.ExpenseDto;
import com.diazdevin.financetracker.Model.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpenseService {

    Expense save(ExpenseDto expenseDto);

    Expense getExpenseById(Long id);

    Expense update(Long id, ExpenseDto expenseDto);

    void delete(Long id);

    List<Expense> findAllExpensesById(Long id);

}
