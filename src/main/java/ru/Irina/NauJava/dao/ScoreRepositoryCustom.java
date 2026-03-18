package ru.Irina.NauJava.dao;

import ru.Irina.NauJava.entity.Score;
import java.util.List;

public interface ScoreRepositoryCustom {

    // Аналог findByDifficultyAndTitle
    List<Score> findByDifficultyAndTitle(String difficulty, String title);

    // Аналог findByComposerName
    List<Score> findByComposerName(String composerName);
}
