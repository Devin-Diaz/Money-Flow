package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.UserDto;
import com.diazdevin.financetracker.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(UserDto userDto);
}
