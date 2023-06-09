package com.arkinefed.luminous.service;

import com.arkinefed.luminous.data.SamplePackInformation;
import com.arkinefed.luminous.model.SamplePack;
import com.arkinefed.luminous.repository.SamplePackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SamplePackService {
    private final SamplePackRepository samplePackRepository;

    public SamplePackService(SamplePackRepository samplePackRepository) {
        this.samplePackRepository = samplePackRepository;
    }

    public void saveSamplePack(SamplePack samplePack) {
        samplePackRepository.save(samplePack);
    }

    public List<SamplePackInformation> getAllSamplePacks() {
        return samplePackRepository.getAllSamplePacks();
    }

    public SamplePackInformation getSamplePack(long id) {
        return samplePackRepository.getSamplePack(id);
    }

    public SamplePack findById(long id) {
        return samplePackRepository.findById(id).get();
    }

    public boolean existsById(long id) {
        return samplePackRepository.existsById(id);
    }
}
