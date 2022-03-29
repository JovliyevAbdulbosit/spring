package com.security.jwtlearning.services;

import com.security.jwtlearning.domen.UserTable;
import com.security.jwtlearning.repository.UserTableRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTableService {
    private final UserTableRepository userTableRepository;
    private final PasswordEncoder passwordEncoder ;

    public UserTableService(UserTableRepository userTableRepository, PasswordEncoder passwordEncoder) {
        this.userTableRepository = userTableRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserTable create(UserTable userTable){
        userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
        return userTableRepository.save(userTable);

    }
    public boolean checkUser( String user){
        return userTableRepository.existsByUsername(user);
    }

    public List<UserTable> getAll() {
        return userTableRepository.findAll();
    }
    public UserTable getUser(String user){
        return userTableRepository.findByUsername(user);
    }
}
