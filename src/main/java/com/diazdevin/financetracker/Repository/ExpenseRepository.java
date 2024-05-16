package com.diazdevin.financetracker.Repository;

import com.diazdevin.financetracker.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Expense findById(long id);
    List<Expense> findAllByUserId(Long id);
}
