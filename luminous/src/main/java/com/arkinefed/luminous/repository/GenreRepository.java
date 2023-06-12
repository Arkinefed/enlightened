package com.arkinefed.luminous.repository;

import com.arkinefed.luminous.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findById(long id);

    Genre findByName(String name);

    boolean existsByName(String name);

    @Query("select g.name from Genre g")
    List<String> getNames();
}
