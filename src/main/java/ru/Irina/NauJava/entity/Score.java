package ru.Irina.NauJava.entity;

import jakarta.persistence.*;

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
}