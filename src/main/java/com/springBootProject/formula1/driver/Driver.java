package com.springBootProject.formula1.driver;

import javax.persistence.*;

@Entity
@Table(name = "drivers_1997")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String team;

    public Driver() {
    }

    public Driver(String name,
                  String team) {
        this.name = name;
        this.team = team;
    }

    public Driver(Long id,
                  String name,
                  String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
