package com.springBootProject.formula1.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE d.name =?1")
    Optional<Driver> findByName(String name);

    List<Driver> findByYear(int year);

    List<Driver> findByTeam(String team);

    boolean existsByName(String name);

    long countByTeam(String team);
}