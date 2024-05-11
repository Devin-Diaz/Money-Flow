package com.diazdevin.financetracker.Repository;

import com.diazdevin.financetracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserEmail(String email);
}
