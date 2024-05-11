package com.diazdevin.financetracker.Service;

import com.diazdevin.financetracker.Dto.UserDto;
import com.diazdevin.financetracker.Model.User;
import com.diazdevin.financetracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
