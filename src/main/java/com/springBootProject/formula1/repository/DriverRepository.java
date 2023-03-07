package com.springBootProject.formula1.repository;

import com.springBootProject.formula1.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    List<Driver> findAllByOrderById();
    List<Driver> findByPositionOrderById(int position);
    List<Driver> findByYearAndTeamOrderById(int year, String team);
    List<Driver> findByYearOrderById(int year);
    List<Driver> findByTeamOrderById(String team);
    boolean existsByNameAndYearAndTeam(String name, int year, String team);
}