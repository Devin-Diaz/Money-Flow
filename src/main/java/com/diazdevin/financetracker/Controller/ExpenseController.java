package com.diazdevin.financetracker.Controller;

import com.diazdevin.financetracker.Dto.ExpenseDto;
import com.diazdevin.financetracker.Dto.UserDto;
import com.diazdevin.financetracker.Security.CustomUserDetails;
import com.diazdevin.financetracker.Service.ExpenseService;
import com.diazdevin.financetracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

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
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getId();
        }
        throw new RuntimeException("User not authenticated");
    }

    @GetMapping("/new-budget")
    public String getBudgetPage() {
        return "new-budget";
    }

    @PostMapping("/new-budget")
    public String setBudget(@ModelAttribute("budget") BigDecimal budget, Principal principal) {
        // Assuming you have a way to get the user ID from the principal
        // This might vary based on your authentication implementation
        Long userId = getCurrentUserId();
        userService.initializeBudget(userId, budget);
        return "redirect:/user-page"; // Redirect to user dashboard or appropriate page
    }




}
