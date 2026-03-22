package ru.Irina.NauJava.service;

import ru.Irina.NauJava.entity.Score;
import java.util.List;

public interface ScoreService {
    void createScore(Long id, String title, String composer,
                     String instrument, String genre, String difficulty);
    Score findById(Long id);
    void deleteById(Long id);
    void updateTitle(Long id, String newTitle);
    List<Score> findByComposer(String composer);
    List<Score> findAll();
}
