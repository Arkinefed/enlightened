package com.arkinefed.luminous.controller;

import com.arkinefed.luminous.data.LoginRequest;
import com.arkinefed.luminous.data.LoginResponse;
import com.arkinefed.luminous.data.RegisterRequest;
import com.arkinefed.luminous.data.RegisterResponse;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.service.UserService;
import com.arkinefed.luminous.utility.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (!userService.existsByUsername(loginRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new LoginResponse(false));
        }

        User user = userService.findByUsername(loginRequest.getUsername());

        if (Authorization.verifyPassword(loginRequest.getPassword(), user.getPassword())) {
            String header = "{" +
                    "\"typ\" : \"JWT\"," +
                    "\"alg\" : \"HS256\"" +
                    "}";

            LocalDateTime date = LocalDateTime.now();
            ZoneId zone = ZoneId.systemDefault();

            String payload = "{" +
                    "\"name\" : \"" + user.getUsername() + "\"," +
                    "\"role\" : \"" + user.getRole() + "\"," +
                    "\"exp\" : \"" + date.plusMinutes(5).atZone(zone).toEpochSecond() + "\"" +
                    "}";

            try {
                String token = Authorization.generateToken(header, payload);

                return ResponseEntity.ok(new LoginResponse(true, user.getUsername(), token));
            } catch (NoSuchAlgorithmException | InvalidKeyException e) {
                return ResponseEntity.status(500).body(new LoginResponse(false));
            }
        }

        return ResponseEntity.badRequest().body(new LoginResponse(false));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new RegisterResponse(false, "username taken"));
        }

        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                User.Role.user,
                LocalDateTime.now()
        );

        userService.saveUser(user);

        return ResponseEntity.ok(new RegisterResponse(true, "registered"));
    }
}
