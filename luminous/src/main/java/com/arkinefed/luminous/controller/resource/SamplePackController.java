package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.service.SamplePackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource/sample-pack")
@CrossOrigin(origins = "http://localhost:8081")
public class SamplePackController {
    private final SamplePackService samplePackService;

    public SamplePackController(SamplePackService samplePackService) {
        this.samplePackService = samplePackService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSamplePacks() {
        return ResponseEntity.ok(samplePackService.getAllSamplePacks());
    }
}
