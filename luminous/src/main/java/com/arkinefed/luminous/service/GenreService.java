package com.arkinefed.luminous.service;

import com.arkinefed.luminous.data.genre.GenreInformation;
import com.arkinefed.luminous.model.Genre;
import com.arkinefed.luminous.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findById(long id) {
        return genreRepository.findById(id);
    }

    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public boolean existsById(long id) {
        return genreRepository.existsById(id);
    }

    public boolean existsByName(String name) {
        return genreRepository.existsByName(name);
    }

    public List<String> getNames() {
        return genreRepository.getNames();
    }

    public void deleteByName(String name) {
        genreRepository.delete(genreRepository.findByName(name));
    }

    public void updateGenre(Genre genre) {
        genreRepository.save(genre);
    }

    public List<GenreInformation> getGenresInformation() {
        return genreRepository.getGenresInformation();
    }
}
