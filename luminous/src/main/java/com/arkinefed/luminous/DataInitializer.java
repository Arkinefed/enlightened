package com.arkinefed.luminous;

import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.repository.UserRepository;
import com.arkinefed.luminous.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User("Arkinefed", "x", "Adrian", "Ciesielski", User.Role.admin, LocalDateTime.now());

        userService.saveUser(admin);
    }
}
