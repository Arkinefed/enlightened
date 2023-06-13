package com.arkinefed.luminous.service;

import com.arkinefed.luminous.data.account.UserInformationAdmin;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<UserInformationAdmin> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
