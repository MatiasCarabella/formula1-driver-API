package com.springBootProject.formula1.domain;

import jakarta.persistence.*;




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

    public Driver() {
    }

    public Driver(int year,
                  String name,
                  String team,
                  int position,
                  double points,
                  int wins,
                  int podiums) {
        this.year = year;
        this.name = name;
        this.team = team;
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.podiums = podiums;
    }

    public Driver(Long id,
                  int year,
                  String name,
                  String team,
                  int position,
                  double points,
                  int wins,
                  int podiums) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.team = team;
        this.position = position;
        this.points = points;
        this.wins = wins;
        this.podiums = podiums;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getYear() {return year; }
    public void setYear(int year) { this.year = year; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTeam() { return team;}
    public void setTeam(String team) { this.team = team; }

    public int getPosition() {return position; }
    public void setPosition(int position) { this.position = position; }

    public double getPoints() {return points; }
    public void setPoints(double points) { this.points = points; }

    public int getWins() {return wins; }
    public void setWins(int wins) { this.wins = wins; }

    public int getPodiums() {return podiums; }
    public void setPodiums(int podiums) { this.podiums = podiums; }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", points='" + points + '\'' +
                ", wins='" + wins + '\'' +
                ", podiums='" + podiums + '\'' +
                '}';
    }
}
