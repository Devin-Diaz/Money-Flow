package com.diazdevin.financetracker.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_table")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category")
    private String category;

    @Column(name = "payee")
    private String payee;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense(String category, String payee, String description, BigDecimal amount, String date) {
        this.category = category;
        this.payee = payee;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Expense(String category, String payee, String description, BigDecimal amount, String date, User user) {
        this.category = category;
        this.payee = payee;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

}
