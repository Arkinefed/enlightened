package com.arkinefed.luminous.controller.resource;

import com.arkinefed.luminous.service.SamplePackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSamplePack(@PathVariable long id) {
        if (samplePackService.existsById(id)) {
            return ResponseEntity.ok(samplePackService.getSamplePack(id));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
