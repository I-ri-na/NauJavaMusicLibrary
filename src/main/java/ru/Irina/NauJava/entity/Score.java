package ru.Irina.NauJava.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String difficulty;

    @Column
    private String description;

    @ManyToOne
    private Composer composer;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "score")
    private List<Favorite> favorites;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Composer getComposer() { return composer; }
    public void setComposer(Composer composer) { this.composer = composer; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<Favorite> getFavorites() { return favorites; }
    public void setFavorites(List<Favorite> favorites) { this.favorites = favorites; }
}
