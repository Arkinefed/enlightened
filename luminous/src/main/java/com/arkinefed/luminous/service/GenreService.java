package com.arkinefed.luminous.service;

import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }
}
