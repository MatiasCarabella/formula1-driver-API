package com.springBootProject.formula1.domain;

import javax.persistence.*;




@Entity
@Table(name = "drivers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"year", "name", "team"})
)
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

    public Driver() {
    }

    public Driver(int year,
                  String name,
                  String team) {
        this.year = year;
        this.name = name;
        this.team = team;
    }

    public Driver(Long id,
                  int year,
                  String name,
                  String team) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {return year; }

    public void setYear(int year) { this.year = year; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
