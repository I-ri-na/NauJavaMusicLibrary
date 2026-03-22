package ru.Irina.NauJava.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "composers")
public class Composer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer birthYear;

    @Column
    private String nationality;

    @OneToMany(mappedBy = "composer")
    private List<Score> scores;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }
    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public List<Score> getScores() { return scores; }
    public void setScores(List<Score> scores) { this.scores = scores; }
}
