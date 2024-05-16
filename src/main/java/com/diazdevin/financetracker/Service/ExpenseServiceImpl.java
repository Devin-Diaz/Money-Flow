package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.ExpenseConverter;
import com.diazdevin.financetracker.Dto.ExpenseDto;
import com.diazdevin.financetracker.Model.Expense;
import com.diazdevin.financetracker.Model.User;
import com.diazdevin.financetracker.Repository.ExpenseRepository;
import com.diazdevin.financetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Expense save(ExpenseDto expenseDto) {
        Long userId = expenseDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Expense expense = ExpenseConverter.convertDtoToEntity(expenseDto, user);

        BigDecimal currentBudget = user.getBudget();
        BigDecimal newExpense = expense.getAmount();
        BigDecimal updatedBudget = currentBudget.subtract(newExpense);
        user.setBudget(updatedBudget);
        expense.setUser(user);

        userRepository.save(user);
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    @Override
    public Expense update(Long id, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense with id " + id + " not found"));

        BigDecimal oldBAmount = existingExpense.getAmount();
        BigDecimal newAmount = expenseDto.getAmount();
        BigDecimal difference = newAmount.subtract(oldBAmount);

        User user = existingExpense.getUser();
        Expense updatedExpense = ExpenseConverter.convertDtoToEntity(expenseDto, user);
        updatedExpense.setId(id); // Ensure the ID is set for updating the existing entity

        user.setBudget(user.getBudget().subtract(difference));

        userRepository.save(user);
        return expenseRepository.save(updatedExpense);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense with id " + id + " not found"));

        User user = expense.getUser();

        BigDecimal currentBudget = user.getBudget();
        BigDecimal expenseAmount = expense.getAmount();
        BigDecimal newBudget = currentBudget.add(expenseAmount);
        user.setBudget(newBudget);

        expenseRepository.delete(expense);
        userRepository.save(user);
    }

    @Override
    public List<Expense> findAllExpensesById(Long id) {
        return expenseRepository.findAllByUserId(id);
    }

}

