package com.arkinefed.luminous.service;

import com.arkinefed.luminous.model.SamplePack;
import com.arkinefed.luminous.repository.SamplePackRepository;
import org.springframework.stereotype.Service;

@Service
public class SamplePackService {
    private final SamplePackRepository samplePackRepository;

    public SamplePackService(SamplePackRepository samplePackRepository) {
        this.samplePackRepository = samplePackRepository;
    }

    public void saveSamplePack(SamplePack samplePack) {
        samplePackRepository.save(samplePack);
    }
}
