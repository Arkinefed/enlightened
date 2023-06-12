package com.arkinefed.luminous.repository;

import com.arkinefed.luminous.data.sample_pack.SamplePackInformation;
import com.arkinefed.luminous.model.SamplePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SamplePackRepository extends JpaRepository<SamplePack, Long> {
    @Query("select new com.arkinefed.luminous.data.sample_pack.SamplePackInformation(sp.id, sp.name, sp.price, sp.genre.name, sp.description, sp.releaseDate) from SamplePack sp")
    List<SamplePackInformation> getAllSamplePacks();

    @Query("select new com.arkinefed.luminous.data.sample_pack.SamplePackInformation(sp.id, sp.name, sp.price, sp.genre.name, sp.description, sp.releaseDate) from SamplePack sp where sp.id = :id")
    SamplePackInformation getSamplePack(long id);

    @Query("select new com.arkinefed.luminous.data.sample_pack.SamplePackInformation(sp.id, sp.name, sp.price, sp.genre.name, sp.description, sp.releaseDate) from SamplePack sp where lower(sp.name) like lower(concat('%', :search, '%')) or lower(sp.description) like lower(concat('%', :search, '%'))")
    List<SamplePackInformation> getAllSamplePacksWithNameOrDescriptionLike(String search);

    boolean existsById(long id);

    boolean existsByName(String name);
}
