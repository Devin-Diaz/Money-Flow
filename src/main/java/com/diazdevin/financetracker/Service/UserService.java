package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.UserDto;
import com.diazdevin.financetracker.Model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface UserService {

    User saveUser(UserDto userDto);

    User findUserById(Long id);

    User initializeBudget(Long userId, BigDecimal budget);

}
