package ru.Irina.NauJava.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "favorites")
public class Favorite {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate addedDate;

    @Column
    private String notes;

    @Column
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "score_id", nullable = false)
    private Score score;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getAddedDate() { return addedDate; }
    public void setAddedDate(LocalDate addedDate) { this.addedDate = addedDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Score getScore() { return score; }
    public void setScore(Score score) { this.score = score; }
}
