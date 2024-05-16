package com.diazdevin.financetracker.Controller;

import com.diazdevin.financetracker.Dto.ExpenseDto;
import com.diazdevin.financetracker.Security.CustomUserDetails;
import com.diazdevin.financetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/new-expense")
    public String newExpense(Model model) {
        model.addAttribute("expense", new ExpenseDto());

        Long userId = getCurrentUserId();
        model.addAttribute("userId", userId);

        return "new-expense";
    }

    @PostMapping("/new-expense")
    public String saveExpense(@ModelAttribute("expense") ExpenseDto expenseDto, Model model) {
        Long userId = getCurrentUserId();
        expenseDto.setUserId(userId);

        // Log the userId to ensure it's set correctly
        System.out.println("User ID: " + userId);
        System.out.println("Expense DTO: " + expenseDto);

        expenseService.save(expenseDto);
        return "redirect:/user-page";
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }
}
