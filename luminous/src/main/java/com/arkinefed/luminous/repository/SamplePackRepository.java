package com.arkinefed.luminous.repository;

import com.arkinefed.luminous.model.SamplePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplePackRepository extends JpaRepository<SamplePack, Long> {

}
