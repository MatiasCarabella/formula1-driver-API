package com.motorsport.formula1.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "drivers",
    uniqueConstraints = @UniqueConstraint(columnNames = {"year", "name", "team"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Basic(optional = false)
  @Column(nullable = false)
  private int year;

  @Basic(optional = false)
  @Column(nullable = false)
  private String name;

  @Basic(optional = false)
  @Column(nullable = false)
  private String team;

  @Basic(optional = false)
  @Column(nullable = false)
  private int position;

  @Basic(optional = false)
  @Column(nullable = false)
  private double points;

  @Basic(optional = false)
  @Column(nullable = false)
  private int wins;

  @Basic(optional = false)
  @Column(nullable = false)
  private int podiums;
}
