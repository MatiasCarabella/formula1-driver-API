package com.springBootProject.formula1.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

//    @Query("SELECT d FROM Driver d WHERE d.id =?1")
//    Optional<Driver> findById(Long id);

    @Query("SELECT d FROM Driver d WHERE d.name =?1")
    Optional<Driver> findByName(String name);

    boolean existsByName(String name);

    long countByTeam(String team);
}