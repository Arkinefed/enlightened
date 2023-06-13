package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.data.account.UserInformation;
import com.arkinefed.luminous.data.account.UserInformationAdmin;
import com.arkinefed.luminous.data.genre.GenreInformation;
import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.service.UserService;
import com.arkinefed.luminous.utility.Authorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/resource/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);
            User user = userService.findByUsername(username);
            UserInformation userInformation = new UserInformation(user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRegisterDate(),
                    user.getOrders().size());

            return ResponseEntity.ok(userInformation);
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<?> admin(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);
            User user = userService.findByUsername(username);

            if (user.getRole() == User.Role.admin) {
                return ResponseEntity.ok(true);
            }

            return ResponseEntity.status(403).body("access denied");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);

            User user = userService.findByUsername(username);

            if (user.getRole() != User.Role.admin) {
                return ResponseEntity.status(403).body("access denied");
            }

            return ResponseEntity.ok(userService.getAllUsers());
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody UserInformationAdmin userInformationAdmin) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);

            User user = userService.findByUsername(username);

            if (user.getRole() != User.Role.admin) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (!userService.existsByUsername(userInformationAdmin.getUsername())) {
                return ResponseEntity.status(400).body("user doesn't exist");
            }

            userService.delete(userService.findByUsername(userInformationAdmin.getUsername()));

            return ResponseEntity.ok("user removed");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody UserInformationAdmin userInformationAdmin) {
        String token = bearer.split("\\s+")[1];

        try {
            if (!Authorization.verifyToken(token)) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (Authorization.tokenExpired(token)) {
                return ResponseEntity.status(401).body("token expired");
            }

            String username = Authorization.getValueFromTokenPayload("username", token);

            User user = userService.findByUsername(username);

            if (user.getRole() != User.Role.admin) {
                return ResponseEntity.status(403).body("access denied");
            }

            if (!userService.existsByUsername(userInformationAdmin.getUsername())) {
                return ResponseEntity.status(400).body("user doesn't exist");
            }

            User u = userService.findByUsername(userInformationAdmin.getUsername());

            u.setUsername(userInformationAdmin.getUsername());
            u.setPassword(userInformationAdmin.getPassword());
            u.setRole(userInformationAdmin.getRole());
            u.setFirstName(userInformationAdmin.getFirstName());
            u.setLastName(userInformationAdmin.getLastName());
            u.setRegisterDate(userInformationAdmin.getRegisterDate());

            userService.updateUser(u);

            return ResponseEntity.ok("user updated");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }
}
