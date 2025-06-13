package com.motorsport.formula1.repository;

import com.motorsport.formula1.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {

    boolean existsByNameAndYearAndTeam(String name, int year, String team);

}