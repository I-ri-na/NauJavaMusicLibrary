package ru.Irina.NauJava.entity;

public class Score {
    private Long id;
    private String title;
    private String composer;
    private String instrument;
    private String genre;
    private String difficulty;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getComposer() { return composer; }
    public void setComposer(String composer) { this.composer = composer; }

    public String getInstrument() { return instrument; }
    public void setInstrument(String instrument) { this.instrument = instrument; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " | " + composer +
                " | " + instrument + " | " + genre + " | " + difficulty;
    }
}
