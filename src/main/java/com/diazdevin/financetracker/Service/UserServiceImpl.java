package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.UserDto;
import com.diazdevin.financetracker.Model.User;
import com.diazdevin.financetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(UserDto userDto) {
        User user = new User(userDto.getUserFirstName(), userDto.getUserLastName(),
                userDto.getUserEmail(), passwordEncoder.encode(userDto.getUserPassword()), userDto.getUserRole());

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Transactional
    @Override
    public User initializeBudget(Long userId, BigDecimal initialBudget) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Check if the budget is already set and skip or update based on your business rules
        if (user.getBudget() == null || BigDecimal.ZERO.compareTo(user.getBudget()) == 0) {
            user.setBudget(initialBudget);
            userRepository.save(user);
        } else {
            throw new IllegalStateException("Budget already initialized for user ID: " + userId);
        }

        return user;
    }

}
