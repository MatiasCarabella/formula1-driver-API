package com.springBootProject.formula1.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findAllByOrderById();
    List<Driver> findByYearAndTeam(int year, String team);
    List<Driver> findByYear(int year);
    List<Driver> findByTeam(String team);
    boolean existsByNameAndYearAndTeam(String name, int year, String team);
}