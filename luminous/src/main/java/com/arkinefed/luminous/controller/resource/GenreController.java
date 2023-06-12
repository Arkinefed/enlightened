package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.data.genre.GenreInformation;
import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.repository.UserRepository;
import com.arkinefed.luminous.service.GenreService;
import com.arkinefed.luminous.utility.Authorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RestController
@RequestMapping("/resource/genre")
@CrossOrigin(origins = "http://localhost:8081")
public class GenreController {
    private final GenreService genreService;
    private final UserRepository userService;

    public GenreController(GenreService genreService,
                           UserRepository userService) {
        this.genreService = genreService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                 @RequestBody GenreInformation genreInformation) {
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

            if (genreService.existsByName(genreInformation.getName())) {
                return ResponseEntity.status(400).body("genre exists");
            }

            genreService.saveGenre(new Genre(genreInformation.getName()));

            return ResponseEntity.ok("genre added");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok(genreService.getGenresInformation());
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody GenreInformation genreInformation) {
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

            if (!genreService.existsByName(genreInformation.getName())) {
                return ResponseEntity.status(400).body("genre doesn't exist");
            }

            genreService.deleteByName(genreInformation.getName());

            return ResponseEntity.ok("genre removed");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody GenreInformation genreInformation) {
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

            if (!genreService.existsById(genreInformation.getId())) {
                return ResponseEntity.status(400).body("genre doesn't exist");
            }

            if (genreService.existsByName(genreInformation.getName())) {
                return ResponseEntity.status(400).body("genre exists");
            }

            Genre g = genreService.findById(genreInformation.getId());

            g.setName(genreInformation.getName());

            genreService.updateGenre(g);

            return ResponseEntity.ok("genre updated");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }
}
