package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.data.genre.GenreInformation;
import com.arkinefed.luminous.data.sample_pack.AddSamplePackRequest;
import com.arkinefed.luminous.data.sample_pack.SamplePackInformation;
import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.model.SamplePack;
import com.arkinefed.luminous.model.User;
import com.arkinefed.luminous.service.GenreService;
import com.arkinefed.luminous.service.SamplePackService;
import com.arkinefed.luminous.service.UserService;
import com.arkinefed.luminous.utility.Authorization;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/resource/sample-pack")
@CrossOrigin(origins = "http://localhost:8081")
public class SamplePackController {
    private final SamplePackService samplePackService;
    private final UserService userService;
    private final GenreService genreService;

    public SamplePackController(SamplePackService samplePackService,
                                UserService userService,
                                GenreService genreService) {
        this.samplePackService = samplePackService;
        this.userService = userService;
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSamplePacks() {
        return ResponseEntity.ok(samplePackService.getAllSamplePacks());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSamplePack(@PathVariable long id) {
        if (samplePackService.existsById(id)) {
            return ResponseEntity.ok(samplePackService.getSamplePack(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{part}")
    public ResponseEntity<?> find(@PathVariable String part) {
        return ResponseEntity.ok(samplePackService.findSamplePacks(part));
    }

    @PostMapping(value = "/add", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> add(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                 @ModelAttribute AddSamplePackRequest request) {
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

            if (samplePackService.existsByName(request.getName())) {
                return ResponseEntity.status(400).body("sample pack exists");
            }

            SamplePack sp = new SamplePack(
                    request.getName(),
                    request.getPrice(),
                    genreService.findByName(request.getGenre()),
                    request.getDescription(),
                    request.getReleaseDate());

            sp.setImage(request.getImage().getBytes());

            samplePackService.saveSamplePack(sp);

            return ResponseEntity.ok("sample pack added");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody SamplePackInformation samplePackInformation) {
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

            if (!samplePackService.existsByName(samplePackInformation.getName())) {
                return ResponseEntity.status(400).body("sample pack doesn't exist");
            }

            samplePackService.delete(samplePackService.findById(samplePackInformation.getId()));

            return ResponseEntity.ok("sample pack removed");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer,
                                    @RequestBody SamplePackInformation samplePackInformation) {
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

            if (!samplePackService.existsById(samplePackInformation.getId())) {
                return ResponseEntity.status(400).body("sample pack doesn't exist");
            }

            SamplePack sp = samplePackService.findById(samplePackInformation.getId());

            sp.setName(samplePackInformation.getName());
            sp.setPrice(samplePackInformation.getPrice());

            Genre g = genreService.findByName(samplePackInformation.getGenreName());

            if (g == null) {
                g = new Genre(samplePackInformation.getGenreName());
            }

            sp.setGenre(g);
            sp.setDescription(samplePackInformation.getDescription());
            sp.setReleaseDate(samplePackInformation.getReleaseDate());

            samplePackService.updateSamplePack(sp);

            return ResponseEntity.ok("sample pack updated");
        } catch (NoSuchAlgorithmException | InvalidKeyException | JsonProcessingException e) {
            return ResponseEntity.status(500).body("internal error");
        }
    }
}
